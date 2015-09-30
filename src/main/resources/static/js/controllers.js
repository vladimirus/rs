function SearchController($scope, $stateParams, Search, $location) {
    if (!$scope.query) {
        $scope.query = $location.search().q;
    }

    if ($scope.query) {
        $scope.links = searchRs($scope.query);
    }

    $scope.submit = function () {
        if ($scope.query) {
            $scope.links = searchRs($scope.query);
        }
    };

    function searchRs(query) {
        $location.search('q', query);
        Pace.restart();
        return Search.query({query: query});
    }
}

angular
    .module('rs')
    .controller('SearchController', SearchController);