app.controller('funcionarioListController', function($scope, funcionarioService) {
    // Mockando dados dos funcionários
    $scope.funcionarios = funcionarioService.getFuncionarios();

    // Função para agrupar funcionários por liderança
    function agruparFuncionariosPorLideranca(funcionarios) {
        var grupos = {};
        funcionarios.forEach(function(funcionario) {
            var liderancaId = funcionario.lideranca ? funcionario.lideranca.id : 'root';
            if (!grupos[liderancaId]) {
                grupos[liderancaId] = [];
            }
            grupos[liderancaId].push(funcionario);
        });
        return grupos;
    }

    // Agrupar funcionários por liderança
    $scope.gruposFuncionarios = agruparFuncionariosPorLideranca($scope.funcionarios);

    // Função para expandir/recolher sublistas
    $scope.toggleGrupo = function(funcionario) {
        funcionario.expanded = !funcionario.expanded;
    };

    // Função para obter funcionários liderados por um funcionário específico
    $scope.getLiderados = function(funcionario) {
        return $scope.gruposFuncionarios[funcionario.id] || [];
    };

    // Função para construir a hierarquia de funcionários
    function buildHierarquia(gruposFuncionarios) {
        var hierarquia = [];
        var grupoRoot = gruposFuncionarios['root'];
        grupoRoot.forEach(function(funcionario) {
            var hierarquiaFuncionario = angular.copy(funcionario);
            hierarquiaFuncionario.funcionarios = $scope.getLiderados(funcionario).map(function(liderado) {
                var lideradoHierarquia = angular.copy(liderado);
                lideradoHierarquia.funcionarios = $scope.getLiderados(liderado).map(function(subLiderado) {
                    return angular.copy(subLiderado);
                });
                return lideradoHierarquia;
            });
            hierarquia.push(hierarquiaFuncionario);
        });
        return hierarquia;
    }

    // Construir hierarquia
    $scope.hierarquiaFuncionarios = buildHierarquia($scope.gruposFuncionarios);
});
