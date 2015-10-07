'use strict';

angular.module('superGolApp')
  .controller('TeamsCtrl', function (teamsService, $scope, $http, $mdSidenav, $mdBottomSheet, $log) {

    $scope.model = {teams:[]};

    teamsService
      .all()
      .then( function( teams ) {
        $scope.model.teams    = teams;
      });
  });
