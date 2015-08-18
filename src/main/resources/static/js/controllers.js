function SearchController($scope, $location) {
    $scope.submit = function () {
        //$scope.query = 'search is not implemented yet :)';
        $scope.links = [
            { uri: "http://google.com", text: $scope.query },
            { uri: "http://google.com", text: "And another" },
            { uri: "http://google.com", text: "Some more" }
        ];
    };
}

angular
    .module('rs')
    .controller('SearchController', SearchController);