'use strict';

describe('Service: userDataService', function () {

  // load the service's module
  beforeEach(module('WorkFinderApp'));

  // instantiate service
  var userDataService;
  beforeEach(inject(function (_userDataService_) {
    userDataService = _userDataService_;
  }));

  it('should do something', function () {
    expect(!!userDataService).toBe(true);
  });

});
