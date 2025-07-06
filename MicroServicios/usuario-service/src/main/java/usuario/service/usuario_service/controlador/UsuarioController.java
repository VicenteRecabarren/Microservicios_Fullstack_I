package usuario.service.usuario_service.controlador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import usuario.service.usuario_service.entidades.Usuario;
import usuario.service.usuario_service.modelo.Curso;
import usuario.service.usuario_service.modelo.Rol;
import usuario.service.usuario_service.servicio.UsuarioServicio;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public ResponseEntity<List<EntityModel<Usuario>>> listarUsuarios() {
        List<Usuario> usuarios = usuarioServicio.obtenerTodosLosUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EntityModel<Usuario>> recursos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            Rol rol = usuarioServicio.obtenerRolDeUsuario(usuario.getRolId());
            usuario.setRol(rol);
            List<Integer> cursoIds = usuario.getCursoIds();
            EntityModel<Usuario> recurso = EntityModel.of(usuario);
            recurso.add(linkTo(methodOn(UsuarioController.class).obtenerUsuario(usuario.getId())).withSelfRel());
            recurso.add(linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("todos-usuarios"));
            for (Integer cursoId : cursoIds) {
                recurso.add(org.springframework.hateoas.Link.of("http://localhost:8003/cursos/" + cursoId).withRel("curso-" + cursoId));
            }
            recursos.add(recurso);
        }
        return ResponseEntity.ok(recursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> obtenerUsuario(@PathVariable("id") int id) {
        Usuario usuario = usuarioServicio.conseguirUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setRol(usuarioServicio.obtenerRolDeUsuario(usuario.getRolId()));
        EntityModel<Usuario> recurso = EntityModel.of(usuario);
        recurso.add(linkTo(methodOn(UsuarioController.class).obtenerUsuario(id)).withSelfRel());
        recurso.add(linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("todos-usuarios"));
        for (Integer cursoId : usuario.getCursoIds()) {
            recurso.add(org.springframework.hateoas.Link.of("http://localhost:8003/cursos/" + cursoId).withRel("curso-" + cursoId));
        }
        return ResponseEntity.ok(recurso);
    }


    // GET /usuarios/email/{String email}
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> obtenerUsuarioPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioServicio.buscarPorEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioServicio.guardarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("id") int id){
        Usuario usuario = usuarioServicio.conseguirUsuarioPorId(id);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        usuarioServicio.borrarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // GET /usuarios/cursos/{cursoId}
    @GetMapping("/cursos/{cursoId}")
    public ResponseEntity<List<EntityModel<Usuario>>> obtenerUsuariosPorCurso(@PathVariable int cursoId) {
    List<Usuario> usuarios = usuarioServicio.obtenerUsuariosPorCurso(cursoId);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EntityModel<Usuario>> recursos = new ArrayList<>();
        usuarios.forEach(usuario -> {
            usuario.setCurso(usuarioServicio.obtenerCursoPorId(cursoId));
            EntityModel<Usuario> recurso = EntityModel.of(usuario);
            recurso.add(linkTo(methodOn(UsuarioController.class).obtenerUsuario(usuario.getId())).withSelfRel());
            recurso.add(linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("todos-usuarios"));
            recurso.add(linkTo(methodOn(UsuarioController.class).obtenerUsuariosPorCurso(cursoId)).withSelfRel());
            recurso.add(org.springframework.hateoas.Link.of("http://localhost:8003/cursos/" + cursoId).withRel("curso"));
            recursos.add(recurso);
        });
    return ResponseEntity.ok(recursos);
    }   

    @GetMapping("/{usuarioId}/cursos")
    public ResponseEntity<List<EntityModel<Curso>>> obtenerCursosDeUsuario(@PathVariable int usuarioId) {
        List<Curso> cursos = usuarioServicio.obtenerCursosDeUsuario(usuarioId);
        if (cursos == null || cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Curso>> recursos = new ArrayList<>();
        for (Curso curso : cursos) {
            EntityModel<Curso> recurso = EntityModel.of(curso);
            recurso.add(linkTo(methodOn(UsuarioController.class).obtenerCursosDeUsuario(usuarioId)).withSelfRel());
            recurso.add(linkTo(methodOn(UsuarioController.class).obtenerUsuario(usuarioId)).withRel("usuario"));
            recurso.add(org.springframework.hateoas.Link.of("http://localhost:8003/cursos/" + curso.getId()).withSelfRel());
            recursos.add(recurso);
        }

        return ResponseEntity.ok(recursos);
    }

    @PutMapping("/{usuarioId}/asignar-curso/{cursoId}")
    public ResponseEntity<EntityModel<Usuario>> asignarCursoAUsuario(
        @PathVariable int usuarioId,
        @PathVariable int cursoId) {
        
        Usuario usuarioActualizado = usuarioServicio.asignarCursoUsuario(usuarioId, cursoId);
            if (usuarioActualizado == null) {
            return ResponseEntity.notFound().build();
            }

        EntityModel<Usuario> recurso = EntityModel.of(usuarioActualizado);
        recurso.add(linkTo(methodOn(UsuarioController.class).obtenerUsuario(usuarioId)).withSelfRel());
        recurso.add(linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("todos-usuarios"));
        recurso.add(org.springframework.hateoas.Link.of("http://localhost:8003/cursos/" + cursoId).withRel("curso-asignado"));

        return ResponseEntity.ok(recurso);
    }
}