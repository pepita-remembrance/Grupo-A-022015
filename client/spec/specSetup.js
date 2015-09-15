var $httpBackend, $scope, $state, getController;

beforeEach(function() {
  module('superGolApp');
  module('stateMock');

  inject(function($controller, $rootScope, _$httpBackend_, _$state_) {
    $httpBackend = _$httpBackend_;
    $scope = $rootScope.$new();
    $state = _$state_;

    getController = function(name, dependencies) {
      const defaults = _.partialRight(_.assign, function(a, b) {
        if (typeof a === "undefined") {
          return b;
        } else {
          return a;
        }
      });

      const defaultDependencies = {
        $scope: $scope,
        $httpBackend: $httpBackend,
        $state: $state
      };

      return $controller(name, defaults(defaultDependencies, dependencies));
    };
  });
});
