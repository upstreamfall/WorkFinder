'use strict';

angular.module('WorkFinderApp')
  .service('userDataService', function (apiRoot, $http) {
    this.getUserData = function (userId) {
      return $http.get(apiRoot + '/user/' + userId + '/userData');
    };

    this.updateUserData = function (userId, userData) {
      return $http.post(apiRoot + '/user/' + userId + '/userData', userData);
    };
  });
