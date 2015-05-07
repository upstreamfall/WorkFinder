'use strict';

angular
  .module('WorkFinderApp', [
    'ngAnimate',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngMockE2E',
    'LocalStorageModule'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .run(function ($rootScope, $location, loginService) {
    $rootScope.$on("$routeChangeStart", function (event, next, current) {

      $rootScope.hideHeader = !!($location.path() === '/login');
      if (!loginService.isAuthenticated()) {
        if (next.templateUrl == "views/login.html") {
        } else {
          $location.path("/login");
        }
      }
    });
  });
