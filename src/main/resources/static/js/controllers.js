function SearchController($scope, $location) {
    $scope.submit = function () {
        $scope.query = 'search is not implemented yet :)'
    };
}

angular
    .module('rs')
    .controller('SearchController', SearchController);