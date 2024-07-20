app.controller('funcionarioListController', ['$scope', 'funcionarioService', '$window', function($scope, funcionarioService, $window) {
    // Carregar lista de funcionários
    funcionarioService.getFuncionarios().then(function(response) {
        $scope.funcionarios = response.data;
    });
    
    $scope.editarFuncionario = function (funcionario) {
        // Redirecionar para a tela de cadastro com os dados do funcionário para edição
        $window.location.href = '#!/cadastro?funcionarioId=' + funcionario.id;
    };

    $scope.excluirFuncionario = function (id) {
        if ($window.confirm('Tem certeza que deseja excluir este funcionário?')) {
            funcionarioService.deleteFuncionario(id).then(function (response) {
                $window.alert('Funcionário excluído com sucesso!');
                $scope.funcionarios = $scope.funcionarios.filter(func => func.id !== id);
            }, function (error) {
                $window.alert('Erro ao excluir funcionário.');
            });
        }
    };
}]);
