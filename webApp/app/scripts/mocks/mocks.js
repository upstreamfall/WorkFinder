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

    // mało restowo, ale jebać
    $httpBackend.whenGET('/skills/Backend/subskills').respond(function () {
      return [200, [
        {name: 'C#'},
        {name: 'Java'}
      ]];
    });

    $httpBackend.whenGET('/skills/Frontend/subskills').respond(function () {
      return [200, [
        {name: 'JS'},
        {name: 'CSS'}
      ]];
    });

    $httpBackend.whenGET('/skills/JS/subskills').respond(function () {
      return [200, [
        {name: 'Angular'},
        {name: 'Meteor'}
      ]];
    });

    $httpBackend.whenGET('/skills/Angular/subskills').respond(function () {
      return [200, []];
    });

    $httpBackend.whenGET('/skills/Meteor/subskills').respond(function () {
      return [200, []];
    });

    $httpBackend.whenGET('/skills/C#/subskills').respond(function () {
      return [200, [
        {name: 'EF'}
      ]];
    });

    $httpBackend.whenGET('/skills/Java/subskills').respond(function () {
      return [200, []];
    });

    $httpBackend.whenGET('/skills/Java/skillsIndividual').respond(function () {
      return [200, [
        {name: 'jakiesgowno'},
        {name: 'jakiesgowno2'}
      ]];
    });

    $httpBackend.whenGET('/skills/EF/skillsIndividual').respond(function () {
      return [200, [
        {name: 'jakiesgowno3'}
      ]];
    });

    $httpBackend.whenGET('/skills/Angular/skillsIndividual').respond(function () {
      return [200, [
        {name: 'jakiesgowno4'}
      ]];
    });

    $httpBackend.whenGET('/skills/Meteor/skillsIndividual').respond(function () {
      return [200, [
        {name: 'jakiesgowno5'}
      ]];
    });

    $httpBackend.whenGET('/skillsRoot').respond(function () {
      return [200, [
        {name: 'Backend'},
        {name: 'Frontend'}
      ]];
    });

    $httpBackend.whenGET('/user/1/skillset').respond(function () {
      return [200, [
        {name: 'Java', level: 2},
        {name: 'JS', level: 3}
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
