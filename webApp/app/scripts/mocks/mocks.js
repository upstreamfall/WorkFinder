'use strict';

angular.module('WorkFinderApp')
  .run(function ($httpBackend) {
    $httpBackend.whenPOST('/login').respond(function (method, url, data) {
        return [200, {
          login: angular.fromJson(data).login,
          name: 'John Smith',
          email: 'john.smith@gmail.com',
          birthday: '2015-04-16',
          phoneNumber: '555-223-445',
          placeOfLiving: 'Warsaw, ul. Jana Pawła 23'
        }, {}];
    });

    $httpBackend.whenGET('/userData').respond(function () {
      return [200, {
        name: 'John Smith',
        email: 'john.smith@gmail.com'
      }];
    });

    $httpBackend.whenPOST('/userData').respond(function (method, url, data) {
      return [200, data];
    });

    $httpBackend.whenGET('/skills').respond(function () {
      return [200, [
        {id: 1, name: 'Java'},
        {id: 2, name: 'JS'},
        {id: 3, name: 'MeteorJS'}
      ]];
    });

    $httpBackend.whenGET('/skillset').respond(function () {
      return [200, [
        {skillId: 1, levelId: 2},
        {skillId: 3, levelId: 3}
      ]];
    });

    $httpBackend.whenGET('/levels').respond(function () {
      return [200, [
        {id: 1, name: 'Low'},
        {id: 2, name: 'Intermediate'},
        {id: 3, name: 'High'},
        {id: 4, name: 'Guru'}
      ]];
    });

    $httpBackend.whenPOST('/skillset').respond(function (method, url, data) {
      return [200, data];
    });

    // Catch-all pass through for all other requests
    $httpBackend.whenGET(/.*/).passThrough();
    $httpBackend.whenPOST(/.*/).passThrough();
    $httpBackend.whenDELETE(/.*/).passThrough();
    $httpBackend.whenPUT(/.*/).passThrough();
  });
