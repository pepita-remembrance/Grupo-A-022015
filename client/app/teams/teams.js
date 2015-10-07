angular
  .module('superGolApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('teams', {
        url: '/teams',
        templateUrl: 'app/teams/teams.html',
        parent:'main',
        controller: 'TeamsCtrl'
      });
  });
