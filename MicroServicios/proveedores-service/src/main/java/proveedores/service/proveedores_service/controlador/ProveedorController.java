package proveedores.service.proveedores_service.controlador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proveedores.service.proveedores_service.entidades.Proveedor;
import proveedores.service.proveedores_service.servicio.ProveedorServicio;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorServicio proveedorServicio;

    @GetMapping
    public ResponseEntity<List<Proveedor>> listarProveedores() {
        List<Proveedor> proveedores = proveedorServicio.obtenerTodosLosProveedores();
        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Proveedor>> obtenerProveedor(@PathVariable("id") int id) {
        Proveedor proveedor = proveedorServicio.conseguirProveedorPorId(id);
        if (proveedor == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Proveedor> recurso = EntityModel.of(proveedor);
        recurso.add(linkTo(methodOn(ProveedorController.class).obtenerProveedor(id)).withSelfRel());
        recurso.add(linkTo(methodOn(ProveedorController.class).listarProveedores()).withRel("todos-proveedores"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorServicio.guardarProveedor(proveedor);
        return ResponseEntity.ok(nuevoProveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable("id") int id) {
        Proveedor proveedor = proveedorServicio.conseguirProveedorPorId(id);
        if (proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        proveedorServicio.borrarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
