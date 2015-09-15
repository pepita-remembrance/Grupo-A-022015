'use strict';

describe('Controller: MainCtrl', function () {
  beforeEach(module('superGolApp'));

  var MainCtrl,
      scope,
      $httpBackend;

  beforeEach(inject(function (_$httpBackend_, $controller, $rootScope) {
    $httpBackend = _$httpBackend_;
    $httpBackend.expectGET('/api/imports')
      .respond([
        { code: 123145, createdAt: "2015-09-15T05:51:58.976Z" }
      ]);

    scope = $rootScope.$new();
    MainCtrl = $controller('MainCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of imports to the scope', function () {
    $httpBackend.flush();
    expect(scope.imports.length).toBe(1);
  });
});
