'use strict';

angular.module('WorkFinderApp')
  .service('skillsDataService', function ($http, apiRoot) {
    this.getSkillsData = function () {
      return $http.get(apiRoot + '/skillset');
    };

    this.getSkills = function () {
      return $http.get(apiRoot + '/skills');
    };

    this.getLevels = function () {
      return $http.get(apiRoot + '/levels');
    };

    this.updateSkillsData = function (skillsData) {
      return $http.post(apiRoot + '/skillset', skillsData);
    };
  });
