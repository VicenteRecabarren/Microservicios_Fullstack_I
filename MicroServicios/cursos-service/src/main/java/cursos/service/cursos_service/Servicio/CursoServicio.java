package cursos.service.cursos_service.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cursos.service.cursos_service.Entidades.Curso;
import cursos.service.cursos_service.Repositorio.CursoRepositorio;

@Service
public class CursoServicio {

    @Autowired
    private CursoRepositorio cursoRepositorio;
    
    public List<Curso> getAll(){
        return cursoRepositorio.findAll();
    }
    
    public List<Curso> obtenerTodos(){
        return cursoRepositorio.findAll();
    }

    public Curso getCursoById(int id){
        return cursoRepositorio.findById(id).orElse(null);
    }
    
    public Curso obtenerPorId(int id){
        return cursoRepositorio.findById(id).orElse(null);
    }

    public Curso save(Curso curso){
        Curso nuevoCurso = cursoRepositorio.save(curso);
        return nuevoCurso;
    }

    public List<Curso> byUsuarioId(int usuarioid){
        return cursoRepositorio.findByUsuarioid(usuarioid);
    }
}
