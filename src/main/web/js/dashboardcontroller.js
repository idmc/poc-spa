/**
 * Created by ralph on 11/05/15.
 */
angular.module('idmc')
    .controller('DashboardCtrl', ['$scope', '$http', '$interval','$filter', function ($scope, $http, $interval,$filter) {
        $scope.countries = {};
        $scope.mapLayer = null;
        $scope.customMap = {};
        $scope.currentPeriod = 0;
        $scope.currentPeriodKey = "";
        $scope.countryShapes = null;

        $scope.sliderValue = "10";
        $scope.sliderOptions = {
            "from": 1, "to": 100, "step": 1, callback: function () {
                showMapLayer();
            }
        };

        $scope.filter = $scope.usedFilter = {"chosenTypes": [], "counts": "period", "interval": "year"};


        var stop;

        $scope.startMap = function () {
            L.Icon.Default.imagePath = 'images';

            var osmUrl = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
            var osmAttrib = 'Map data © <a href="http://openstreetmap.org">OpenStreetMap</a> contributors';
            var osm = new L.TileLayer(osmUrl, {minZoom: 1, maxZoom: 20, attribution: osmAttrib});

            var customMap = L.map('customMap').setView([51.505, -0.09], 4);
            customMap.addLayer(osm);

            var legend = L.control({position: 'bottomleft'});
            legend.onAdd = function (map) {
                var div = L.DomUtil.create('div', 'info legend'),
                    grades = [0, 10, 20, 50, 100, 200, 500, 1000],
                    labels = [];

                // loop through our density intervals and generate a label with a colored square for each interval
                for (var i = 0; i < grades.length; i++) {
                    div.innerHTML +=
                        '<i style="background:' + doDetermineColor(grades[i] + 1) + '"></i> ' +
                        grades[i] + (grades[i + 1] ? '&ndash;' + grades[i + 1] + '<br>' : '+');
                }

                return div;
            };

            legend.addTo(customMap);

            $scope.customMap = customMap;

            $http.get('/js/countries.json').success(function (data) {
                $scope.countryShapes = data;
                $scope.refreshData();
            });
        };

        $scope.refreshData = function () {
            $scope.usedFilter = angular.copy($scope.filter);
            var request = {};
            request.interval = $scope.filter.interval;
            request.startDate = $filter('date')($scope.filter.startDate,'yyyy-MM-dd');
            request.endDate = $filter('date')($scope.filter.endDate,'yyyy-MM-dd');
            request.types = $scope.filter.chosenTypes;

            $http.post('/query/countries', request).success(function (periods) {
                $scope.periods = [];
                $scope.countries = {};

                var prevPeriod;
                angular.forEach(periods, function (period) {
                    var currPeriod = {};

                    angular.forEach(period.counts.counts, function (country, countryKey) {
                        currPeriod[countryKey]=country;
                        if (prevPeriod && prevPeriod.counts[countryKey]) {
                            currPeriod[countryKey].cumulative=country.count+prevPeriod.counts[countryKey].cumulative;
                        } else {
                            currPeriod[countryKey].cumulative=country.count;
                        }
                    });

                    if (prevPeriod) {
                        angular.forEach(prevPeriod.counts, function(country, countryKey) {
                            if (!currPeriod[countryKey]) {
                                currPeriod[countryKey] = country;
                                currPeriod[countryKey].count = 0;
                            }
                        });
                    }

                    var newPeriod = {"key": period.key, "counts": currPeriod};
                    $scope.periods.push(newPeriod);
                    prevPeriod = angular.copy(newPeriod);
                });


                $scope.sliderOptions.from = 1;
                $scope.sliderOptions.to = periods.length - 1;
                $scope.sliderOptions.step = 1;
                showMapLayer();
            });
        };

        $scope.startPlay = function () {
            if (angular.isDefined(stop)) return;
            stop = $interval(function () {
                if ($scope.currentPeriod < ($scope.periods.length - 1)) {
                    $scope.nextPeriod();
                } else {
                    $scope.pausePlay();
                }
            }, 1000);
        };

        $scope.pausePlay = function () {
            if (angular.isDefined(stop)) {
                $interval.cancel(stop);
                stop = undefined;
            }
        };

        $scope.nextPeriod = function () {
            $scope.currentPeriod++;
            showMapLayer();
        };

        $scope.previousPeriod = function () {
            $scope.currentPeriod--;
            showMapLayer();
        };

        $scope.restartPlay = function () {
            $scope.currentPeriod = 0;
            showMapLayer();
        };

        function showMapLayer() {
            if ($scope.mapLayer) {
                $scope.customMap.removeLayer($scope.mapLayer);
            }
            if ($scope.periods && $scope.periods.length > 0) {
                $scope.countries = $scope.periods[$scope.currentPeriod].counts;
                $scope.currentPeriodKey = $scope.periods[$scope.currentPeriod].key;
                $scope.mapLayer = L.geoJson($scope.countryShapes, {style: style});
                $scope.customMap.addLayer($scope.mapLayer);
            }
        }

        function determineColor(data) {
            var country = data.properties.name;
            if ($scope.countries[country]) {
                var d = ($scope.usedFilter && $scope.usedFilter.counts === 'period') ? $scope.countries[country].count:$scope.countries[country].cumulative;
                return doDetermineColor(d);
            } else {
                return '#ccc';
            }
        }

        function doDetermineColor(amount) {
            return amount > 1000 ? '#800026' :
                amount > 500 ? '#BD0026' :
                    amount > 200 ? '#E31A1C' :
                        amount > 100 ? '#FC4E2A' :
                            amount > 50 ? '#FD8D3C' :
                                amount > 20 ? '#FEB24C' :
                                    amount > 10 ? '#FED976' :
                                        amount > 0 ? '#FFEDA0':
                '#ccc';
        }

        function style(feature) {
            return {
                fillColor: determineColor(feature),
                weight: 2,
                opacity: 1,
                color: 'white',
                dashArray: '3',
                fillOpacity: 0.7
            };
        }


        $scope.initTypes = function () {
            $http.get('/query/types').success(function (types) {
                $scope.types = types;
            });
        };

        $scope.addSpaTypeToFilter = function () {
            var i = $scope.filter.chosenTypes.indexOf($scope.selectType);
            if (i == -1) {
                $scope.filter.chosenTypes.push($scope.selectType);
            }
            $scope.selectType = "";
        };

        $scope.removeSpaTypeFromFilter = function (index) {
            $scope.filter.chosenTypes.splice(index, 1);
        };

        $scope.today = function () {
            if ($scope.openedStartDate) {
                $scope.filter.startDate = new Date();
            }
            if ($scope.openedEndDate) {
                $scope.filter.endDate = new Date();
            }
        };

        $scope.clear = function () {
            if ($scope.openedStartDate) {
                $scope.filter.startDate = null;
            }
            if ($scope.openedEndDate) {
                $scope.filter.endDate = null;
            }
        };

        $scope.openStartDate = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.openedStartDate = true;
        };

        $scope.openEndDate = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.openedEndDate = true;
        };

        $scope.dateOptions = {
            formatYear: 'yyyy',
            startingDay: 1
        };

        $scope.startMap();
        $scope.initTypes();

    }]);