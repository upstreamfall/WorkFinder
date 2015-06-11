'use strict';

angular.module('WorkFinderApp')
  .controller('OffersCtrl', function ($timeout, $rootScope, $scope, offersService) {
    var userId = $rootScope.userId;

    $scope.fetchOffers = function () {
      $scope.showSpinner = true;
      offersService.getOffers(userId).success(function (offers) {
        $scope.offers = offers;
        $timeout(function() {
          $scope.showSpinner = false;
          $scope.showOffers = true;
        }, 1000);
      });
    };
  });
