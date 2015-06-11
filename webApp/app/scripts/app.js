'use strict';

angular
  .module('WorkFinderApp', [
    'ngAnimate',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngMockE2E',
    'LocalStorageModule',
    'toastr'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/offers.html',
        controller: 'OffersCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/offers', {
        templateUrl: 'views/offers.html',
        controller: 'OffersCtrl'
      })
      .when('/myData', {
        templateUrl: 'views/mydata.html',
        controller: 'MydataCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .run(function ($rootScope, $location, loginService) {
    $rootScope.$on('$routeChangeStart', function (event, next) {
      $rootScope.hideHeader = ($location.path() === '/login');
      if (!loginService.isAuthenticated()) {
        if (next.templateUrl === 'views/login.html') {
        } else {
          $location.path('/login');
        }
      }
    });
  });
