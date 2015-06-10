'use strict';

angular.module('WorkFinderApp')
  .controller('OffersCtrl', function ($scope, offersService) {
    offersService.getOffers().success(function (offers) {
        $scope.offers = offers;
      });
  });
