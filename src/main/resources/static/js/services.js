function SearchFactory($resource) {
    return $resource('/api/search/:query', {
        query: '@query'
    });
}

angular
    .module('rs')
    .factory('Search', SearchFactory);