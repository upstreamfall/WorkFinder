'use strict';

describe('Controller: OffersCtrl', function () {

  // load the controller's module
  beforeEach(module('workFinderApp'));

  var OffersCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OffersCtrl = $controller('OffersCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
