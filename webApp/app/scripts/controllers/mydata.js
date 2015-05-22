'use strict';

angular.module('WorkFinderApp')
  .controller('MydataCtrl', function ($scope, userDataService, toastr) {
    userDataService.getUserData().success(function (userData) {
      $scope.userData = userData;
    });

    $scope.submitUserData = function (userData) {
      userDataService.updateUserData(userData).success(function () {
        toastr.success('Your data has been saved!', 'Success!');
      }).error(function () {
        toastr.success('There was a problem while saving data', 'Error!');
      });
    };
  });
