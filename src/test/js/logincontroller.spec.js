/**
 * Created by ralph on 12/05/15.
 */
describe('LoginCtrl', function () {
    beforeEach(module('dmd'));

    var $controller, $httpBackend, $location;

    beforeEach(inject(function (_$controller_, _$location_, _$httpBackend_) {
        // The injector unwraps the underscores (_) from around the parameter names when matching
        $controller = _$controller_;
        $location = _$location_;
        $httpBackend = _$httpBackend_;

        $httpBackend.when('POST', '/login').respond(200);
    }));

    describe('$scope.login', function () {
        var $scope, controller;

        beforeEach(function () {
            $scope = {};
            controller = $controller('LoginCtrl', {$scope: $scope});
        });

        it('Location changed', function () {
            $scope.login({username: "user", password: "password"});
            $httpBackend.expectGET('/');
        });

        it("fail", function () {
            expect("").toMatch("error");
        })
    });
});