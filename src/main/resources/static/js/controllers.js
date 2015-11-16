function SearchController($scope, $stateParams, Search, Suggest, Query, $location) {
    $scope.appName = "reddit searcher";

    $scope.tabs = [
        { id: "web", heading:"Web", active: false },
        { id: "images", heading:"Images", active: false }
    ];

    $scope.tabs[activateTab($scope.tabs, $location.search().t || "web")].active = true;

    if (!$scope.query) {
        $scope.query = $location.search().q;
    }

    if ($scope.query) {
        parseResponse(executeSearch($scope.query, $location.search().p || 1, $location.search().t || "web"), $scope);
    }

    $scope.focusInput = true;
    $scope.submit = function () {
        if ($scope.query) {
            parseResponse(executeSearch($scope.query, 1, $location.search().t || "web"), $scope);
        }
    };

    $scope.pageChanged = function() {
        $scope.pageTypeChanged($location.search().t || "web");
    };

    $scope.pageTypeChanged = function(type) {
        parseResponse(executeSearch($scope.query, $scope.currentPage, type), $scope);
    };


    $scope.getSuggestions = function(query) {
        return Suggest.query({query: query}).$promise.then(function(data) {
            return data;
        });
    };

    $scope.filterTopic = function (topic) {
        var apendedQuery = $scope.query + " subreddit: " + topic;
        Query.get({query: apendedQuery}).$promise.then(function(data) {
            $scope.query = data.query;
            $scope.focusInput = true;
        });
    };

    function activateTab(tabs, current) {
        for (i = 0; i < tabs.length; i++) {
            if (current === tabs[i].id) {
                return i;
            }
        }
        return 0;
    }

    function parseResponse(reponse, $scope) {
        reponse.$promise.then(function(data) {
            $scope.links = data.links;

            $scope.maxSize = 5;
            $scope.itemsPerPage = data.elementsPerPage;

            $scope.totalItems = data.totalElements;
            $scope.currentPage = data.currentPage + 1;

            $scope.topics = data.topics;
        });
    }

    function executeSearch(query, pageNo, type) {
        $location.search('q', query);
        $location.search('p', pageNo);
        $location.search('t', type);
        Pace.restart();
        return searchRs(query, pageNo, type);
    }

    function searchRs(query, pageNo, type) {
        return Search.get({query: query, pageNo: pageNo, type: type});
    }
}

angular
    .module('rs')
    .controller('SearchController', SearchController);