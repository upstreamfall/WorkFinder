'use strict';

angular.module('WorkFinderApp')
  .controller('MydataCtrl', function ($rootScope, $compile, $scope, userDataService, skillsDataService, toastr) {
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

    $scope.addSubSkill = function(rootIndex, event) {
        skillsDataService.getSubSkills($scope.roots[rootIndex].name).success(function (subskills) {
          var skillsSelect = $compile('<sub-skills-select ng-options="skill.name as skill.name for skill in subskills"></sub-skills-select>')( $scope );
          angular.element(event.currentTarget).parent().append(skillsSelect);
        });
    };
  });
