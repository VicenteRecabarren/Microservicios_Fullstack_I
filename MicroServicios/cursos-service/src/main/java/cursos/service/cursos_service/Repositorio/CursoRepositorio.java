package cursos.service.cursos_service.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cursos.service.cursos_service.Entidades.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Integer>{
    List<Curso> findByUsuarioid(int usuarioid);

}
