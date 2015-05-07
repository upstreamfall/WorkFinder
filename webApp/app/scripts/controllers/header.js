'use strict';

angular.module('WorkFinderApp')
  .controller('HeaderCtrl', function ($scope, loginService) {
      $scope.logout = function () {
        loginService.logout();
      };
  });
