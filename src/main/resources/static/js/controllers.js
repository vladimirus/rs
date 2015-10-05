function SearchController($scope, $stateParams, Search, $location) {
    $scope.appName = "reddit searcher";

    if (!$scope.query) {
        $scope.query = $location.search().q;
    }

    if ($scope.query) {
        $scope.links = executeSearch($scope.query, 0);
    }

    $scope.submit = function () {
        if ($scope.query) {
            $scope.links = executeSearch($scope.query, 0);
        }
    };

    function executeSearch(query, pageNo) {
        $location.search('q', query);
        Pace.restart();
        return searchRs(query, pageNo);
    }

    function searchRs(query, pageNo) {
        return Search.query({query: query, pageNo: pageNo});
    }
}

angular
    .module('rs')
    .controller('SearchController', SearchController);