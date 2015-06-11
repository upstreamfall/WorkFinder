'use strict';

describe('Service: offersService', function () {

  // load the service's module
  beforeEach(module('WorkFinderApp'));

  // instantiate service
  var offersService;
  beforeEach(inject(function (_offersService_) {
    offersService = _offersService_;
  }));

  it('should do something', function () {
    expect(!!offersService).toBe(true);
  });

});
