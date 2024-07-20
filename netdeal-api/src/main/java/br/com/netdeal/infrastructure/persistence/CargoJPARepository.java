package br.com.netdeal.infrastructure.persistence;

import br.com.netdeal.domain.entity.Cargo;
import br.com.netdeal.domain.entity.CargoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoJPARepository extends JpaRepository<Cargo, CargoId> {
    Optional<Cargo> findByTituloAndNivel(String titulo, int nivel);
    Cargo findByNivel(int nivel);
}
