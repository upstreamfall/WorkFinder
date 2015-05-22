'use strict';

describe('Controller: MydataCtrl', function () {

  // load the controller's module
  beforeEach(module('WorkFinderApp'));

  var MydataCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MydataCtrl = $controller('MydataCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
