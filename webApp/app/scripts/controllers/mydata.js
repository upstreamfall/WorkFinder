'use strict';

angular.module('WorkFinderApp')
  .controller('MydataCtrl', function ($scope, userDataService) {
    userDataService.getUserData().success(function (userData) {
      $scope.userData = userData;
    });

    $scope.submitUserData = function (userData) {
      userDataService.updateUserData(userData);
    };
  });
