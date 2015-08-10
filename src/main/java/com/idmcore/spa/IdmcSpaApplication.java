package com.idmcore.spa;

import com.idmcore.spa.importer.SpaImporter;
import com.idmcore.spa.queries.*;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolFilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogram;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.FilterBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.filteredQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * https://github.com/spring-projects/spring-integration-java-dsl/wiki/Spring-Integration-Java-DSL-Reference
 */
@SpringBootApplication
@EnableIntegration
@IntegrationComponentScan
@RestController
public class IdmcSpaApplication {

    @Autowired
    Client client;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    SpaImporter spaImporter;

    public static void main(String[] args) {
        SpringApplication.run(IdmcSpaApplication.class, args);
    }

    @RequestMapping("/import/types")
    String importTypes() {
        spaImporter.importTypes();
        return "Imported the types";
    }

    @RequestMapping("/import/repeats")
    String importRepeats() {
        spaImporter.importRepeats();
        return "Imported the repeats";
    }

    @RequestMapping("/import/spa")
    String importSpaRecords() {
        spaImporter.importSpaRecords();
        return "Imported the spa records";
    }

    @RequestMapping("/import/zipcodes")
    String importZipCodes() {
        spaImporter.importZipCodes();
        return "Imported the zip codes";
    }

    @RequestMapping("/import/countrynames")
    String importCountryNames() {
        spaImporter.importCountryNames();
        return "Imported the country names";
    }

    @RequestMapping("/cluster/status")
    ClusterStatus clusterStatus() {

        ClusterHealthResponse clusterIndexHealths = client.admin().cluster().prepareHealth().execute().actionGet();
        String clusterName = clusterIndexHealths.getClusterName();
        switch (clusterIndexHealths.getStatus()) {
            case GREEN:
                return new ClusterStatus(clusterName, "success");
            case YELLOW:
                return new ClusterStatus(clusterName, "warn");
            case RED:
            default:
                return new ClusterStatus(clusterName, "danger");
        }
    }

    @InitBinder("countryRequest")
    protected void initBinderCountryrequest(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "query/countries", method = RequestMethod.POST)
    List<CountryPeriod> countsPerCountry(@RequestBody CountryRequest countryRequest) {

        QueryBuilder queryBuilder = matchAllQuery();
        // Check if we need a filter
        if (countryRequest.getEndDate()!= null ||
                countryRequest.getStartDate() != null ||
                (countryRequest.getTypes() != null && countryRequest.getTypes().size() > 0)) {
            BoolFilterBuilder filterBuilder = boolFilter();
            if (countryRequest.getTypes() != null && countryRequest.getTypes().size() > 0) {
                filterBuilder.must(termsFilter("spaType",countryRequest.getTypes()));
            }
            if (countryRequest.getStartDate() != null) {
                filterBuilder.must(rangeFilter("isolateDate").gte(countryRequest.getStartDate().getTime()));
            }
            if (countryRequest.getEndDate() != null) {
                filterBuilder.must(rangeFilter("isolateDate").lte(countryRequest.getEndDate().getTime()));
            }
            queryBuilder = filteredQuery(queryBuilder,filterBuilder);
        }

        TermsBuilder termsBuilder = AggregationBuilders.terms("byCountry").field("isolationCountry").size(100);
        DateHistogramBuilder interval = AggregationBuilders.dateHistogram("periods").field("isolateDate").interval(new DateHistogram.Interval(countryRequest.getInterval()));
        interval.subAggregation(termsBuilder);

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .addAggregation(interval);

        return elasticsearchTemplate.query(builder.build(), response -> {

            Histogram histogram = response.getAggregations().get("periods");
            return histogram.getBuckets().stream()
                    .map(t -> {
                        Terms terms = t.getAggregations().get("byCountry");
                        Map<String, CountryCount> countList = terms.getBuckets().stream()
                                .map(bucket -> new CountryCount(null, bucket.getKey(), bucket.getDocCount()))
                                .collect(Collectors.toMap(CountryCount::getName, countryCount -> countryCount));
                        return new CountryPeriod(t.getKeyAsText().string(), new CountryCounts(countList));
                    }).collect(Collectors.toList());
        });
    }

    @RequestMapping("query/types")
    List<SpaTypeCount> allTypesWithCount() {
        TermsBuilder termsBuilder = AggregationBuilders.terms("byTypes").field("spaType").size(1000);
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .addAggregation(termsBuilder);

        return elasticsearchTemplate.query(builder.build(), response -> {
            Terms terms = response.getAggregations().get("byTypes");
            return terms.getBuckets().stream()
                    .map(bucket -> new SpaTypeCount(bucket.getKey(), bucket.getDocCount()))
                    .collect(Collectors.toList());
        });
    }
}
