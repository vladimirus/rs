function SearchController($scope, $stateParams, Search, $location) {
    $scope.appName = "reddit searcher";

    if (!$scope.query) {
        $scope.query = $location.search().q;
    }

    if ($scope.query) {
        $scope.links = executeSearch($scope.query);
    }

    $scope.submit = function () {
        if ($scope.query) {
            $scope.links = executeSearch($scope.query);
        }
    };

    function executeSearch(query) {
        $location.search('q', query);
        Pace.restart();
        return searchRs(query);
    }

    function searchRs(query) {
        return Search.query({query: query});
    }
}

angular
    .module('rs')
    .controller('SearchController', SearchController);