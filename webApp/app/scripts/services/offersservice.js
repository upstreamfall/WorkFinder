'use strict';

/**
 * @ngdoc service
 * @name WorkFinderApp.offersService
 * @description
 * # offersService
 * Service in the WorkFinderApp.
 */
angular.module('WorkFinderApp')
  .service('offersService', function ($http, apiRoot) {
    this.getOffers = function (userId) {
      return $http.get(apiRoot + '/user/' + userId + '/offers');
    };
  });
