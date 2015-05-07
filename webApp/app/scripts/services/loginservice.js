'use strict';

angular.module('WorkFinderApp')
  .service('loginService', function (apiRoot, $http, localStorageService) {
    this.login = function (agentName) {
      return $http.post(apiRoot + '/login', {
        login: agentName
      }).then(function (response) {
        // TODO: save some token?
        localStorageService.set('login', response.data.login);
      });
    };

    this.isAuthenticated = function () {
      return !!localStorageService.get('login');
    };

    this.logout = function () {
      return localStorageService.remove('login');
    };
  });
