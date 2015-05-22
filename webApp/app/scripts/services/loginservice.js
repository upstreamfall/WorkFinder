'use strict';

angular.module('WorkFinderApp')
  .service('loginService', function (apiRoot, $http, $location, localStorageService) {
    this.login = function (agentName) {
      return $http.post(apiRoot + '/login', {
        login: agentName
      }).then(function (response) {
        // TODO: save some token?
        localStorageService.set('login', response.data.login);
        $location.path('/');
      });
    };

    this.isAuthenticated = function () {
      return !!localStorageService.get('login');
    };

    this.logout = function () {
      localStorageService.remove('login');
      $location.path('/login');
    };
  });
