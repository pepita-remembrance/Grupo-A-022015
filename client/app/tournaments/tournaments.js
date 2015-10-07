angular.module('superGolApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('tournaments', {
        url: '/tournaments',
        templateUrl: 'app/tournaments/tournaments.html',
        parent:'main',
        controller: 'TournamentsCtrl'
      });
  });
