app.factory('funcionarioService', function($http) {
  var baseUrl = 'http://localhost:8081/funcionarios';
  return {
    getFuncionariosNivel: function(nivel) {
      return $http.get(`${baseUrl}/nivel/` + nivel);
    },

    salvarFuncionario: function(funcionario) {
      return $http.post(`${baseUrl}`, funcionario);
    },

    getFuncionarios: function() {
      return $http.get(`${baseUrl}`);
    },

    getFuncionarioById: function(id) {
      return $http.get(`${baseUrl}/` + id);
    },

    updateFuncionario: function(id, funcionario) {
      return $http.put(`${baseUrl}/` + id, funcionario);
    },

    deleteFuncionario: function(id) {
      return $http.delete(`${baseUrl}/` + id);
    },
    getFuncionarioByLiderancaId:function(id){
      return $http.get(`${baseUrl}/lideranca/` + id);
    }
  };
});
