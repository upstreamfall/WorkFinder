'use strict';

angular.module('WorkFinderApp')
  .directive('skillSelect', function () {
    return {
      templateUrl: 'views/_skillselect.html',
      restrict: 'E',
      scope: {
        skills: '=',
        levels: '=',
        skillLevel: '='
      }
    };
  });
