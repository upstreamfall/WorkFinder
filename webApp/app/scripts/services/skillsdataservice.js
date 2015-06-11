'use strict';

angular.module('WorkFinderApp')
  .service('skillsDataService', function ($http, apiRoot) {
    this.getSkillsData = function (userId) {
      return $http.get(apiRoot + '/user/' + userId + '/skillset');
    };

    this.getSkillsRoot = function () {
      return $http.get(apiRoot + '/skillsRoot');
    };

    this.getLevels = function () {
      return $http.get(apiRoot + '/levels');
    };

    this.updateSkillsData = function (userId, skillsData) {
      return $http.post(apiRoot + '/user/' + userId + '/skillset', skillsData);
    };
  });
