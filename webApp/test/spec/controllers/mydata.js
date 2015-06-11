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
});
