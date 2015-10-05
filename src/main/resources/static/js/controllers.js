function SearchController($scope, $stateParams, Search, $location) {
    $scope.appName = "reddit searcher";

    if (!$scope.query) {
        $scope.query = $location.search().q;
    }

    if ($scope.query) {
        parseResponse(executeSearch($scope.query, 0), $scope);
    }

    $scope.submit = function () {
        if ($scope.query) {
            parseResponse(executeSearch($scope.query, 0), $scope);
        }
    };

    function parseResponse(reponse, $scope) {
        reponse.$promise.then(function(data) {
            $scope.links = data.links;
        });
    }

    function executeSearch(query, pageNo) {
        $location.search('q', query);
        $location.search('p', pageNo);
        Pace.restart();
        return searchRs(query, pageNo);
    }

    function searchRs(query, pageNo) {
        return Search.get({query: query, pageNo: pageNo});
    }
}

angular
    .module('rs')
    .controller('SearchController', SearchController);