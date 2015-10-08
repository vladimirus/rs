function SearchFactory($resource) {
    return $resource('/api/search/:query?p=:pageNo', {
        query: '@query',
        pageNo: '@pageNo'
    });
}

function CommentFactory($resource) {
    return $resource('/api/comments/linkid/:query', {
        get: '@query'
    });
}

function SuggestFactory($resource) {
    return $resource('/api/suggest/:query', {
        query: '@query'
    });
}


angular
    .module('rs')
    .factory('Search', SearchFactory)
    .factory('Comment', CommentFactory)
    .factory('Suggest', SuggestFactory);