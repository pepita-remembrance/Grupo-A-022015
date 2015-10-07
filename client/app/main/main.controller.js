'use strict';

angular.module('superGolApp')
  .controller('MainCtrl', function ($scope, $http, $mdSidenav, $mdBottomSheet, $log, $state) {
    $scope.imports = [];

    const getImports = () =>
      $http.get('/api/imports').success(function(imports) {
        $scope.imports = imports;
      });

    $scope.createImport = () => $http.post('/api/imports', { code: $scope.newImportCode }).then(getImports);
    $scope.toggleList = ()=>{
      $log.debug( "toggleDrawer");
      $mdSidenav('left').toggle()
    };

    $scope.goToTeamsView       = ()=> $state.go('teams');
    $scope.goToTournamentsView = ()=> $state.go('tournaments');


    getImports();
  });
