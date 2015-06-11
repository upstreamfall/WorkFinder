'use strict';

angular.module('WorkFinderApp')
  .controller('MydataCtrl', function ($rootScope, $scope, userDataService, skillsDataService, toastr) {
    var userId = $rootScope.userId;
    userDataService.getUserData(userId).success(function (userData) {
      $scope.userData = userData;
    });

    skillsDataService.getLevels(userId).success(function (levels) {
      $scope.levels = levels;
    });

    skillsDataService.getSkills().success(function (skills) {
      $scope.skills = skills;
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

    $scope.addSkill = function () {
      $scope.skillSet.push({id: '', name: ''});
    };

    $scope.removeSkill = function () {
      $scope.skillSet.pop();
    };
  });
