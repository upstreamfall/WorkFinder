'use strict';

angular.module('WorkFinderApp')
  .run(function ($httpBackend) {
    $httpBackend.whenPOST('/login').respond(function (method, url, data, headers) {
        return [200, {
          login: angular.fromJson(data).login,
          name: 'John Smith',
          email: 'john.smith@gmail.com',
          birthday: '2015-04-16',
          phoneNumber: '555-223-445',
          placeOfLiving: 'Warsaw, ul. Jana Pawła 23'
        }, {}];
    });

    // Catch-all pass through for all other requests
    $httpBackend.whenGET(/.*/).passThrough();
    $httpBackend.whenPOST(/.*/).passThrough();
    $httpBackend.whenDELETE(/.*/).passThrough();
    $httpBackend.whenPUT(/.*/).passThrough();
  });
