/**
 * Created by ralph on 11/05/15.
 */
angular.module('idmc')
    .controller('KibanaCtrl', ['$scope','$sce', function ($scope,$sce) {
        $scope.serverUrl = function() {
            var theUrl = "https://kibana-spa.idmcore.com/#/dashboard/dashboad-1?embed&_g=(time:(from:'1993-01-01T13:41:14.417Z',mode:absolute,to:'2005-06-05T12:56:14.417Z'))&_a=(filters:!(),panels:!((col:1,id:country,row:1,size_x:2,size_y:4,type:visualization),(col:5,id:spa-type,row:1,size_x:2,size_y:4,type:visualization),(col:1,id:map1,row:5,size_x:6,size_y:8,type:visualization),(col:7,id:pie-spa-type,row:3,size_x:3,size_y:3,type:visualization),(col:7,id:area-graph,row:6,size_x:6,size_y:4,type:visualization),(col:3,id:city,row:1,size_x:2,size_y:4,type:visualization),(col:10,id:aquisition,row:1,size_x:3,size_y:3,type:visualization),(col:10,id:origin,row:4,size_x:3,size_y:2,type:visualization),(col:7,id:association,row:1,size_x:3,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'*')),title:'dashboad%201')";
            return $sce.trustAsResourceUrl(theUrl);
        };
    }]);