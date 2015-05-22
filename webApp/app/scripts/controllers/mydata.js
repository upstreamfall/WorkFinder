'use strict';

angular.module('WorkFinderApp')
  .controller('MydataCtrl', function ($scope, userDataService, skillsDataService, toastr) {
    userDataService.getUserData().success(function (userData) {
      $scope.userData = userData;
    });

    skillsDataService.getLevels().success(function (levels) {
      $scope.levels = levels;
    });

    skillsDataService.getSkills().success(function (skills) {
      $scope.skills = skills;
    });

    skillsDataService.getSkillsData().success(function (skillSet) {
      $scope.skillSet = skillSet;
    });

    $scope.submitUserData = function (userData) {
      userDataService.updateUserData(userData).success(function () {
        toastr.success('Your data has been saved!', 'Success!');
      }).error(function () {
        toastr.success('There was a problem while saving data', 'Error!');
      });
    };

    $scope.submitSkillsData = function (skillsData) {
      skillsDataService.updateSkillsData(skillsData).success(function () {
        toastr.success('Your data has been saved!', 'Success!');
      }).error(function () {
        toastr.success('There was a problem while saving data', 'Error!');
      });
    };
  });
