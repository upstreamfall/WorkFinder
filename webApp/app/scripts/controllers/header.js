'use strict';

angular.module('WorkFinderApp')
  .controller('HeaderCtrl', function ($scope, loginService) {
    $scope.logout = function () {
      loginService.logout();
    };

    $scope.headerLinks = [
      {name: 'Home', link: '/'},
      {name: 'Offers', link: '#/offers'}
    ];

    $scope.activeIndex = 0;

    $scope.isActive = function (index) {
      return $scope.activeIndex === index;
    };

    $scope.setActive = function (index) {
      return $scope.activeIndex = index;
    }
  });
