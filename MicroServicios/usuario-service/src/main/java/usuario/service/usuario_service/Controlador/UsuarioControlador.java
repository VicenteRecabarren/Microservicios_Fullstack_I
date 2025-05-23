package usuario.service.usuario_service.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import usuario.service.usuario_service.Entidades.Usuario;
import usuario.service.usuario_service.Modelo.Curso;
import usuario.service.usuario_service.Modelo.Rol;
import usuario.service.usuario_service.Servicio.UsuarioServicio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {


    

    @GetMapping("/rol/{usuarioId}")
    public ResponseEntity<List<Rol>> listarRol(@PathVariable("usuarioId") int id){
        Usuario usuario = usuarioServicio.getUsuarioById(id);
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        List<Rol> roles = usuarioServicio.getRoles(id);
        return ResponseEntity.ok(roles);
    }


    @GetMapping("/curso/{usuarioId}")
    public ResponseEntity<List<Curso>> listarCursos(@PathVariable("usuarioId") int id){
        Usuario usuario = usuarioServicio.getUsuarioById(id);
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        List<Curso> cursos = usuarioServicio.getCursos(id);
        return ResponseEntity.ok(cursos);
    }
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        List<Usuario> usuarios = usuarioServicio.getAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
        Usuario usuario = usuarioServicio.getUsuarioById(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("id")int id){
        Usuario usuario = usuarioServicio.getUsuarioById(id);
        if(usuario == null){
            return ResponseEntity.noContent().build();
        }
        usuarioServicio.deleteUser(id);
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioServicio.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
    
}
