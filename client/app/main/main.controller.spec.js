'use strict';

describe('Controller: MainCtrl', function () {
  beforeEach(() => getController('MainCtrl'));

  it('should attach a list of imports to the scope', function () {
    $httpBackend
      .expectGET('/api/imports')
      .respond([
        { code: 123145, createdAt: "2015-09-15T05:51:58.976Z" }
      ]);

    $httpBackend.flush();

    expect($scope.imports.length).toBe(1);
  });
});
