function topComment(Comment) {
    return {
        restrict: 'A',
        template: '{{commentBody}}',
        link: function (scope, element, attr) {
            attr.$observe('topComment', function(value){
                cmt = Comment.get({query: value});
                cmt.$promise.then(function(data) {
                    scope.commentBody = data.body;
                });
            });
        }
    };
}

angular
    .module('rs')
    .directive('topComment', topComment);