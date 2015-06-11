'use strict';

angular.module('WorkFinderApp')
  .service('loginService', function (apiRoot, $http, $location, localStorageService, $rootScope) {
    this.login = function (agentName) {
      return $http.post(apiRoot + '/login', {
        login: agentName
      }).then(function (response) {
        // TODO: save some token?
        localStorageService.set('login', response.data.login);
        localStorageService.set('id', response.data.id);
        $rootScope.userId = response.data.id;
        $location.path('/');
      });
    };

    this.isAuthenticated = function () {
      $rootScope.login = localStorageService.get('login');
      $rootScope.userId = localStorageService.get('id');
      return !!$rootScope.login;
    };

    this.logout = function () {
      localStorageService.remove('login');
      localStorageService.remove('id');
      $location.path('/login');
    };
  });
