app.directive("funcionarioRow", function() {
    return {
        restrict: "E",
        replace: true,
        scope: {
            funcionarios: "="
        },
        templateUrl: "components/funcionarioRow/funcionarioRow.html",
        controller: function($scope) {
            $scope.toggleGrupo = function(funcionario) {
                funcionario.expanded = !funcionario.expanded;
            };
        }
    };
});
