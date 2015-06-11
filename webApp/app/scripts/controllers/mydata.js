'use strict';

angular.module('WorkFinderApp')
  .controller('MydataCtrl', function ($rootScope, $scope, userDataService, skillsDataService, toastr) {
    var userId = $rootScope.userId;
    $scope.roots = [{name: '' }]

    userDataService.getUserData(userId).success(function (userData) {
      $scope.userData = userData;
    });

    skillsDataService.getLevels(userId).success(function (levels) {
      $scope.levels = levels;
    });

    skillsDataService.getSkillsRoot().success(function (skills) {
      $scope.skillsRoot = skills;
    });

    skillsDataService.getSkillsData(userId).success(function (skillSet) {
      $scope.skillSet = skillSet;
    });

    $scope.submitUserData = function (userData) {
      userDataService.updateUserData(userId, userData).success(function () {
        toastr.success('Your data has been saved!', 'Success!');
      }).error(function () {
        toastr.success('There was a problem while saving data', 'Error!');
      });
    };

    $scope.submitSkillsData = function (skillsData) {
      skillsDataService.updateSkillsData(userId, skillsData).success(function () {
        toastr.success('Your data has been saved!', 'Success!');
      }).error(function () {
        toastr.success('There was a problem while saving data', 'Error!');
      });
    };
  });
