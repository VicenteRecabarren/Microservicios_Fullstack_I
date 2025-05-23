package rol.service.rol_service.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rol.service.rol_service.Entidades.Rol;
import rol.service.rol_service.Servicio.RolServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/rol")
public class RolControlador {

    @Autowired
    private RolServicio rolServicio;

    @GetMapping
    public ResponseEntity<List<Rol>> listarRol() {
        List<Rol> rol = rolServicio.getAll();
        if(rol.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rol);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRol(@PathVariable("id") int id) {
        Rol rol = rolServicio.getRolById(id);   
        if(rol == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }
    
    @PostMapping
    public ResponseEntity<Rol> guardarUsuario(@RequestBody Rol rol) {
        Rol nuevoRol = rolServicio.save(rol);        
        return ResponseEntity.ok(nuevoRol);
    }
    
    @GetMapping("/usuario/{usuarioid}")
    public ResponseEntity <List<Rol>> listarRolesPorUsuarioId(@PathVariable("usuarioid")int id){
        List<Rol> roles = rolServicio.byUsuarioId(id);
        if(roles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);

    }
    
    @PutMapping("/asignar/{rolId}/{usuarioId}")
    public ResponseEntity<Rol> asignaRolUsuario(
        @PathVariable int rolId,
        @PathVariable int usuarioId) {
        
        Rol rol = rolServicio.getRolById(rolId);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        rol.setUsuarioId(usuarioId);
        Rol putRol = rolServicio.save(rol);
        return ResponseEntity.ok(putRol);
    }

}
