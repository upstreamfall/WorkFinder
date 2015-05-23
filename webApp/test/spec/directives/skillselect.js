'use strict';

describe('Directive: skillSelect', function () {

  // load the directive's module
  beforeEach(module('WorkFinderApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<skill-select></skill-select>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the skillSelect directive');
  }));
});
