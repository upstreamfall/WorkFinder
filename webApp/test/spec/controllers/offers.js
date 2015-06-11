'use strict';

describe('Controller: OffersCtrl', function () {

  // load the controller's module
  beforeEach(module('WorkFinderApp'));

  var OffersCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OffersCtrl = $controller('OffersCtrl', {
      $scope: scope
    });
  }));

});
