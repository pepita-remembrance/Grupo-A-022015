'use strict';

angular.module('superGolApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ui.router',
  'ngMaterial'
])
  .config(function ($stateProvider, $urlRouterProvider, $locationProvider, $mdIconProvider) {
    $urlRouterProvider
      .otherwise('/');

    $mdIconProvider
      .iconSet('social', 'app/assets/svg/social-icons.svg', 24)
      .defaultIconSet('app/assets/svg/action-icons.svg', 24);

    $locationProvider.html5Mode(true);
  });
