function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, IdleProvider, KeepaliveProvider) {
    IdleProvider.idle(5);
    IdleProvider.timeout(120);

    $urlRouterProvider.otherwise("/");

    $ocLazyLoadProvider.config({
        debug: true
    });

    $stateProvider
        .state('home', {
            url: "/",
            templateUrl: "home.html"
        })
    ;
}

angular
    .module('rs')
    .config(config)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });
