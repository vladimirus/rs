function SearchFactory($resource) {
    return $resource('/api/search/:query', {
        query: '@query'
    });
}

function CommentFactory($resource) {
    return $resource('/api/comments/linkid/:query', {
        get: '@query'
    });
}

angular
    .module('rs')
    .factory('Search', SearchFactory)
    .factory('Comment', CommentFactory);