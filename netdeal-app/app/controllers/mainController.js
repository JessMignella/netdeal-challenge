app.controller('mainController', ['$scope', 'funcionarioService', function($scope, funcionarioService) {
  $scope.cargos = [
      { titulo: "Estagiário", nivel: 3 },
      { titulo: "Assistente", nivel: 3 },
      { titulo: "Gestor", nivel: 2 },
      { titulo: "Coordenador", nivel: 2 },
      { titulo: "Diretor", nivel: 1 }
  ];

  $scope.funcionario = {};

  $scope.$watch('funcionario.cargo', function(newVal, oldVal) {
      if (newVal && newVal.nivel > 1) {
          funcionarioService.getFuncionariosNivel(newVal.nivel - 1).then(function(response) {
            console.log(response);
              $scope.liderancas = response.data;
          });
      } else {
          $scope.liderancas = [];
          $scope.funcionario.lideranca = null;
      }
  });

  $scope.salvarFuncionario = function() {
      if ($scope.funcionarioForm.$valid) {
          funcionarioService.salvarFuncionario($scope.funcionario).then(function(response) {
              alert('Funcionário salvo com sucesso!');
              $scope.funcionario = {};
          }, function(error) {
              alert('Erro ao salvar funcionário.');
          });
      }
  };
}]);
