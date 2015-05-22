'use strict';

angular.module('WorkFinderApp')
  .controller('HeaderCtrl', function ($scope, $location, loginService) {
    $scope.logout = function () {
      loginService.logout();
    };

    $scope.headerLinks = [
      {name: 'Home', link: '/'},
      {name: 'Offers', link: '/offers'},
      {name: 'MyData', link: '/myData'}
    ];

    var location = $location.path();
    $scope.headerLinks.forEach(function (val, index) {
      if (val.link === location) {
        $scope.activeIndex = index;
      }
    });

    $scope.isActive = function (index) {
      return $scope.activeIndex === index;
    };

    $scope.setActive = function (index) {
      $scope.activeIndex = index;
    };
  });
