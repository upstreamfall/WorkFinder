'use strict';

angular.module('WorkFinderApp')
  .controller('LoginCtrl', function ($scope, $rootScope, $location, loginService) {
    $scope.login = function (name) {
      if (name) {
        loginService.login(name);
      };
    };
  });
