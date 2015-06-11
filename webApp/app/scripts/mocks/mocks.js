'use strict';

angular.module('WorkFinderApp')
  .run(function ($httpBackend) {
    $httpBackend.whenPOST('/login').respond(function (method, url, data) {
        return [200, {
          login: angular.fromJson(data).login,
          id: 1,
          name: 'John Smith',
          email: 'john.smith@gmail.com',
          birthday: '2015-04-16',
          phoneNumber: '555-223-445',
          placeOfLiving: 'Warsaw, ul. Jana Pawła 23'
        }, {}];
    });

    $httpBackend.whenGET('/user/1/userData').respond(function () {
      return [200, {
        name: 'John Smith',
        email: 'john.smith@gmail.com'
      }];
    });

    $httpBackend.whenPOST('/user/1/userData').respond(function (method, url, data) {
      return [200, data];
    });

    $httpBackend.whenGET('/skills').respond(function () {
      return [200, [
        {id: 1, name: 'Java'},
        {id: 2, name: 'JS'},
        {id: 3, name: 'MeteorJS'}
      ]];
    });

    $httpBackend.whenGET('/user/1/skillset').respond(function () {
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

    $httpBackend.whenPOST('/user/1/skillset').respond(function (method, url, data) {
      return [200, data];
    });

    $httpBackend.whenGET('/user/1/offers').respond(function () {
      return [200, [
        // TODO: add data, whatever makes sense
        {id: 1, companyName: 'company'},
        {id: 2, companyName: 'company'},
        {id: 3, companyName: 'company'},
        {id: 4, companyName: 'company'}]
      ];
    });

    // Ca`tch-all pass through for all other requests
    $httpBackend.whenGET(/.*/).passThrough();
    $httpBackend.whenPOST(/.*/).passThrough();
    $httpBackend.whenDELETE(/.*/).passThrough();
    $httpBackend.whenPUT(/.*/).passThrough();
  });
