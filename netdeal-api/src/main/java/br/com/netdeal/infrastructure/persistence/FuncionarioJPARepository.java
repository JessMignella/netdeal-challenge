package br.com.netdeal.infrastructure.persistence;

import br.com.netdeal.domain.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioJPARepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findAllByLiderancaId(Long liderancaId);
    @Query(value = "SELECT f FROM Funcionario f WHERE f.cargo.nivel = :nivel")
    List<Funcionario> findAllByCargoNivel(@Param("nivel") Long nivel);
}
