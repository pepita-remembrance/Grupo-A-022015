'use strict';

angular.module('superGolApp')
  .controller('MainCtrl', function ($scope, $http) {
    $scope.imports = [];

    const getImports = () =>
      $http.get('/api/imports').success(function(imports) {
        $scope.imports = imports;
      });

    $scope.createImport = () => $http.post('/api/imports', { code: $scope.newImportCode }).then(getImports);

    getImports();
  });
