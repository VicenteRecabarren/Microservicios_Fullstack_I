package rol.service.rol_service.controlador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rol.service.rol_service.entidades.Rol;
import rol.service.rol_service.servicio.RolServicio;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolServicio rolServicio;

    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        List<Rol> roles = rolServicio.obtenerTodosLosRoles();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Rol>> obtenerRol(@PathVariable("id") int id) {
        Rol rol = rolServicio.conseguirRolPorId(id);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Rol> recurso = EntityModel.of(rol);
        recurso.add(linkTo(methodOn(RolController.class).obtenerRol(id)).withSelfRel());
        recurso.add(linkTo(methodOn(RolController.class).listarRoles()).withRel("todos-roles"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<Rol> guardarRol(@RequestBody Rol rol) {
        Rol nuevoRol = rolServicio.guardarRol(rol);
        return ResponseEntity.ok(nuevoRol);
    }
}
