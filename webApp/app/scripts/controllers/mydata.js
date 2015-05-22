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

    $scope.levels = [
      {id: 1, name: 'Low'},
      {id: 2, name: 'Intermediate'},
      {id: 3, name: 'High'},
      {id: 4, name: 'Guru'}
    ];

    $scope.skills = [
      {id: 1, name: 'Java'},
      {id: 2, name: 'JS'},
      {id: 3, name: 'MeteorJS'}
    ];

    $scope.skillSet = [
      {skillId: 1, levelId: 2}
    ];

    $scope.print = function() {
      console.log('dasd', $scope.skillSet);
    };
  });
