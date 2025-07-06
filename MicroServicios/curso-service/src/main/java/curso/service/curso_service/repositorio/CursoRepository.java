package curso.service.curso_service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.service.curso_service.entidades.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{

}
