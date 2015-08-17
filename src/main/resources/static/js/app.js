(function () {
    angular.module('rs', [
        'ui.router',                    // Routing
        'oc.lazyLoad',                  // ocLazyLoad
        'ui.bootstrap',                 // Ui Bootstrap
        'ngIdle',                       // Idle timer
        'ngResource'                    // resource for rest services
    ])
})();

// Other libraries are loaded dynamically in the config.js file using the library ocLazyLoad