'use strict';

describe('Service: skillsDataService', function () {

  // load the service's module
  beforeEach(module('WorkFinderApp'));

  // instantiate service
  var skillsDataService;
  beforeEach(inject(function (_skillsDataService_) {
    skillsDataService = _skillsDataService_;
  }));

  it('should do something', function () {
    expect(!!skillsDataService).toBe(true);
  });

});
