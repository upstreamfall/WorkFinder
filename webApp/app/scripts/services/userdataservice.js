'use strict';

angular.module('WorkFinderApp')
  .service('userDataService', function (apiRoot, $http) {
    this.getUserData = function () {
      return $http.get(apiRoot + '/userData');
    };

    this.updateUserData = function (userData) {
      return $http.post(apiRoot + '/userData', userData);
    };
  });
