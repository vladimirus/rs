function SearchController($scope, $stateParams, Search, $location) {
    $scope.submit = function () {
        console.log($scope.query);
        if ($scope.query) {
            Pace.restart();
            var links = Search.query({query: $scope.query}, function () {
                $scope.links = links;
            });
        }
    };
}

angular
    .module('rs')
    .controller('SearchController', SearchController);