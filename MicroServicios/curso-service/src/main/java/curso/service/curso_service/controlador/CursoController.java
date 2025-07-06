package curso.service.curso_service.controlador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import curso.service.curso_service.entidades.Curso;
import curso.service.curso_service.servicio.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<EntityModel<Curso>>> listarCursos() {
        List<Curso> cursos = cursoService.obtenerTodosLosCursos();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EntityModel<Curso>> recursos = cursos.stream().map(curso -> {
            EntityModel<Curso> recurso = EntityModel.of(curso);

            recurso.add(linkTo(methodOn(CursoController.class).obtenerCurso(curso.getId())).withSelfRel());
            recurso.add(linkTo(methodOn(CursoController.class).listarCursos()).withRel("todos-cursos"));
            recurso.add(Link.of("http://localhost:8001/usuarios/cursos/" + curso.getId()).withRel("usuarios-inscritos"));

            // Enlace al proveedor si existe proveedorId
            if (curso.getProveedorId() != 0) {
                recurso.add(Link.of("http://localhost:8004/proveedores/" + curso.getProveedorId()).withRel("proveedor"));
            }
            return recurso;
        }).toList();

        return ResponseEntity.ok(recursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Curso>> obtenerCurso(@PathVariable("id") int id) {
        Curso curso = cursoService.obtenerCursoConProveedor(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Curso> recurso = EntityModel.of(curso);
        recurso.add(linkTo(methodOn(CursoController.class).obtenerCurso(id)).withSelfRel());
        recurso.add(linkTo(methodOn(CursoController.class).listarCursos()).withRel("todos-cursos"));
        recurso.add(Link.of("http://localhost:8001/usuarios/cursos/" + id).withRel("usuarios-inscritos"));

        if (curso.getProveedorId() != 0) {
            recurso.add(Link.of("http://localhost:8004/proveedores/" + curso.getProveedorId()).withRel("proveedor"));
        }

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<Curso> guardarCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.guardarCurso(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable("id") int id) {
        Curso curso = cursoService.conseguirCursoPorId(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        cursoService.borrarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
