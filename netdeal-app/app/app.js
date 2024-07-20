var app = angular.module('myApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/cadastro', {
            templateUrl: 'views/cadastroFuncionario.html',
            controller: 'mainController'
        })
        .when('/funcionarios', {
            templateUrl: 'views/listaFuncionarios.html',
            controller: 'funcionarioListController'
        })
        .otherwise({
            redirectTo: '/cadastro'
        });
});
