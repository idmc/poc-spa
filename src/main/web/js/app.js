/**
 * Created by ralph on 11/05/15.
 */

angular.module('idmc', ['ngRoute', 'ngResource', 'luminis.directives.navbar', 'ui.bootstrap', 'leaflet-directive','angularAwesomeSlider'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/dashboard', {
                controller: 'DashboardCtrl',
                templateUrl: 'dashboard.html'
            })
            .when('/dashboard2', {
                controller: 'KibanaCtrl',
                templateUrl: 'dashboard2.html'
            })
            .otherwise({
                redirectTo: '/dashboard'
            })
    }]);