function SearchController($scope, $stateParams, Search, Suggest, $location) {
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

    $scope.getSuggestions = function(query) {
        return Suggest.query({query: query}).$promise.then(function(data) {
            return data;
        });
    };

    function parseResponse(reponse, $scope) {
        reponse.$promise.then(function(data) {
            $scope.links = getLinks(data);

            $scope.maxSize = 5;
            $scope.itemsPerPage = 10;

            $scope.totalItems = data.totalElements;
            $scope.currentPage = data.currentPage + 1;
        });
    }

    function getLinks(data) {
        var links = data.links;
        for (i = 0; i < links.length; i++) {
            if (links[i].url.length > 33) {
                links[i].shortUrl = links[i].url.substring(0, 30) + "...";
            } else {
                links[i].shortUrl = links[i].url;
            }

            if (links[i].comments !== null) {
                links[i].topComment = links[i].comments[0].body;
            } else {
                links[i].topComment = "comments"
            }
        }
        return links;
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