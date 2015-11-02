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

function focusMe() {
    return {
        scope: { trigger: '=focusMe' },
        link: function(scope, element) {
            scope.$watch('trigger', function(value) {
                if(value === true) {
                    element[0].focus();
                    scope.trigger = false;
                }
            });
        }
    };
};

angular
    .module('rs')
    .directive('focusMe', focusMe)
    .directive('topComment', topComment);