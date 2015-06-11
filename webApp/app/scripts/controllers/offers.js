'use strict';

angular.module('WorkFinderApp')
  .controller('OffersCtrl', function ($rootScope, $scope, offersService) {
    var userId = $rootScope.userId;
    offersService.getOffers(userId).success(function (offers) {
        $scope.offers = offers;
      });
  });
