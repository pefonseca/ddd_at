package management.cursos.infra.repository;

import management.cursos.domain.model.entity.AlunoNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoNotaRepository extends JpaRepository<AlunoNota, Long> {
}
