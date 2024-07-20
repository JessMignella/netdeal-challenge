app.controller('mainController', ['$scope', 'funcionarioService', '$location', function($scope, funcionarioService, $location) {
        $scope.cargos = [
        { titulo: "Estagiario", nivel: 3 },
        { titulo: "Assistente", nivel: 3 },
        { titulo: "Gerente", nivel: 2 },
        { titulo: "Analista", nivel: 2 },
        { titulo: "Supervisor", nivel: 2 },
        { titulo: "Coordenador", nivel: 2 },
        { titulo: "Diretor", nivel: 1 }
    ];

    $scope.funcionario = {};
    $scope.isEdit = false;
    var funcionarioId = $location.search().funcionarioId;
    if (funcionarioId) {
        $scope.isEdit = true;
        funcionarioService.getFuncionarioById(funcionarioId).then(function(response) {
            $scope.funcionario = response.data;
            $scope.funcionario.senha = '';
            funcionarioService.getFuncionariosNivel( $scope.funcionario.cargo.nivel- 1).then(function (response) {
                $scope.liderancas = response.data;
            });
        });
       
    }

    $scope.$watch('funcionario.cargo', function (newVal, oldVal) {
        if (newVal && newVal.nivel > 1) {
            funcionarioService.getFuncionariosNivel(newVal.nivel - 1).then(function (response) {
                $scope.liderancas = response.data;
            });
        } else {
            $scope.liderancas = [];
            $scope.funcionario.lideranca = null;
        }
    });

    $scope.salvarFuncionario = function() {
        if ($scope.funcionarioForm.$valid) {
            if ($scope.isEdit) {
                funcionarioService.updateFuncionario($scope.funcionario.id, $scope.funcionario).then(function(response) {
                    alert('Funcionário atualizado com sucesso!');
                    $scope.funcionario = {};
                    $scope.isEdit = false;
                }, function(error) {
                    alert('Erro ao atualizar funcionário.');
                });
            } else {
                funcionarioService.salvarFuncionario($scope.funcionario).then(function(response) {
                    alert('Funcionário salvo com sucesso!');
                    $scope.funcionario = {};
                }, function(error) {
                    alert('Erro ao salvar funcionário.');
                });
            }
        }
    };

}]);

