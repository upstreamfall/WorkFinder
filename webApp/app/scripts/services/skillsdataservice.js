'use strict';

angular.module('WorkFinderApp')
  .service('skillsDataService', function ($http, apiRoot) {
    this.getSkillsData = function (userId) {
      return $http.get(apiRoot + '/user/' + userId + '/skillset');
    };

    this.getSkills = function () {
      return $http.get(apiRoot + '/skills');
    };

    this.getLevels = function () {
      return $http.get(apiRoot + '/levels');
    };

    this.updateSkillsData = function (userId, skillsData) {
      return $http.post(apiRoot + '/user/' + userId + '/skillset', skillsData);
    };
  });
