function SearchController($scope, $stateParams, Search, $location) {
    $scope.appName = "reddit searcher";

    if (!$scope.query) {
        $scope.query = $location.search().q;
    }

    if ($scope.query) {
        parseResponse(executeSearch($scope.query, $location.search().p || 1), $scope);
    }

    $scope.submit = function () {
        if ($scope.query) {
            parseResponse(executeSearch($scope.query, 1), $scope);
        }
    };

    $scope.pageChanged = function() {
        parseResponse(executeSearch($scope.query, $scope.currentPage), $scope);
    };

    function parseResponse(reponse, $scope) {
        reponse.$promise.then(function(data) {

            var links = data.links;
            for (i = 0; i < links.length; i++) {
                if (links[i].url.length > 33) {
                    links[i].shortUrl = links[i].url.substring(0, 30) + "...";
                } else {
                    links[i].shortUrl = links[i].url;
                }
            }

            $scope.links = data.links;

            $scope.maxSize = 5;
            $scope.itemsPerPage = 10;

            $scope.totalItems = data.totalElements;
            $scope.currentPage = data.currentPage + 1;
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