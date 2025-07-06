package soporte.service.soporte_service.controlador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import soporte.service.soporte_service.entidades.Soporte;
import soporte.service.soporte_service.modelo.Usuario;
import soporte.service.soporte_service.servicio.SoporteServicio;


@RestController
@RequestMapping("/soportes")
public class SoporteController {

    @Autowired
    private SoporteServicio soporteServicio;

    @GetMapping("/{id}/detalle")
    public ResponseEntity<EntityModel<Object>> obtenerSoporteUsuario(@PathVariable int id) {
    Soporte soporte = soporteServicio.conseguirSoportePorId(id);
    if (soporte == null){
        return ResponseEntity.notFound().build();
    }
    Usuario usuario = soporteServicio.obtenerUsuarioPorEmail(soporte.getEmailContacto());
    var respuesta = new Object(){
        public Soporte soporteDetalle = soporte;
        public Usuario usuarioDetalle = usuario;
    };
    EntityModel<Object> recurso = EntityModel.of(respuesta);
    recurso.add(linkTo(methodOn(SoporteController.class).obtenerSoporteUsuario(id)).withSelfRel());
    recurso.add(linkTo(methodOn(SoporteController.class).listarSoportes()).withRel("todos-soportes"));
    return ResponseEntity.ok(recurso);
}

    @GetMapping
    public ResponseEntity<List<EntityModel<Object>>> listarSoportes() {
    List<Soporte> soportes = soporteServicio.obtenerTodosLosSoportes();
    if (soportes.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    List<EntityModel<Object>> recursos = new ArrayList<>();
    for (Soporte soporte : soportes) {
        Usuario usuario = soporteServicio.obtenerUsuarioPorEmail(soporte.getEmailContacto());
        var respuesta = new Object() {
            public Soporte soporteDetalle = soporte;
            public Usuario usuarioDetalle = usuario;
        };
        EntityModel<Object> recurso = EntityModel.of(respuesta);
        recurso.add(linkTo(methodOn(SoporteController.class).obtenerSoporte(soporte.getId())).withSelfRel());
        recurso.add(linkTo(methodOn(SoporteController.class).obtenerSoporteUsuario(soporte.getId())).withRel("detalle-soporte-usuario"));
        recurso.add(linkTo(methodOn(SoporteController.class).listarSoportes()).withRel("todos-soportes"));
        recursos.add(recurso);
    }
    return ResponseEntity.ok(recursos);
}

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Soporte>> obtenerSoporte(@PathVariable("id") int id) {
        Soporte soporte = soporteServicio.conseguirSoportePorId(id);
        if (soporte == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Soporte> recurso = EntityModel.of(soporte);
        recurso.add(linkTo(methodOn(SoporteController.class).obtenerSoporte(id)).withSelfRel());
        recurso.add(linkTo(methodOn(SoporteController.class).listarSoportes()).withRel("todos-soportes"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<Soporte> guardarSoporte(@RequestBody Soporte soporte) {
        Soporte nuevoSoporte = soporteServicio.guardarSoporte(soporte);
        return ResponseEntity.ok(nuevoSoporte);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSoporte(@PathVariable("id") int id) {
        Soporte soporte = soporteServicio.conseguirSoportePorId(id);
        if (soporte == null) {
            return ResponseEntity.notFound().build();
        }
        soporteServicio.borrarSoporte(id);
        return ResponseEntity.noContent().build();
    }
}
