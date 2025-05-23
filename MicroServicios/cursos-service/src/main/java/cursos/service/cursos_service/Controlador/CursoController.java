package cursos.service.cursos_service.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cursos.service.cursos_service.Entidades.Curso;
import cursos.service.cursos_service.Servicio.CursoServicio;


@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoServicio cursoServicio;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCurso(){
        List<Curso> curso = cursoServicio.getAll();
        if(curso.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(curso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable("id") int id){
        Curso curso = cursoServicio.getCursoById(id);
        if(curso == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> guardarCurso(@RequestBody Curso curso){
        Curso nuevoCurso = cursoServicio.save(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    @GetMapping("/usuario/{usuarioid}")
    public ResponseEntity <List<Curso>> listarCursosPorUsuarioId(@PathVariable("usuarioid")int id) {
        List<Curso> curso = cursoServicio.byUsuarioId(id);
        if(curso.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(curso);
    }
    
    @PutMapping("/asignar/{cursoId}/{usuarioId}")
    public ResponseEntity<Curso> asignarCursoUsuario(
        @PathVariable int cursoId,
        @PathVariable int usuarioId) {
        
        Curso curso = cursoServicio.getCursoById(cursoId);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        curso.setUsuarioid(usuarioId);
        Curso putCurso = cursoServicio.save(curso);
        return ResponseEntity.ok(putCurso);
    }


}
