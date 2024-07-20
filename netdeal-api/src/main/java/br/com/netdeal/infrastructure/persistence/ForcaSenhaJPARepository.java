package br.com.netdeal.infrastructure.persistence;

import br.com.netdeal.domain.entity.ForcaSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForcaSenhaJPARepository extends JpaRepository<ForcaSenha, Long> {
}
