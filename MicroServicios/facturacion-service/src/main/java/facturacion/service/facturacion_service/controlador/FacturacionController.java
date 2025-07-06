package facturacion.service.facturacion_service.controlador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import facturacion.service.facturacion_service.entidades.Facturacion;
import facturacion.service.facturacion_service.servicio.FacturacionServicio;


@RestController
@RequestMapping("/facturaciones")
public class FacturacionController {

    @Autowired
    private FacturacionServicio facturacionServicio;

    @GetMapping
    public ResponseEntity<List<Facturacion>> listarFacturaciones() {
        List<Facturacion> facturaciones = facturacionServicio.obtenerTodasLasFacturaciones();
        if (facturaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Facturacion>> obtenerFacturacion(@PathVariable("id") int id) {
        Facturacion facturacion = facturacionServicio.conseguirFacturacionPorId(id);
        if (facturacion == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Facturacion> recurso = EntityModel.of(facturacion);
        recurso.add(linkTo(methodOn(FacturacionController.class).obtenerFacturacion(id)).withSelfRel());
        recurso.add(linkTo(methodOn(FacturacionController.class).listarFacturaciones()).withRel("todas-facturaciones"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<Facturacion> guardarFacturacion(@RequestBody Facturacion facturacion) {
        Facturacion nuevaFacturacion = facturacionServicio.guardarFacturacion(facturacion);
        return ResponseEntity.ok(nuevaFacturacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFacturacion(@PathVariable("id") int id) {
        Facturacion facturacion = facturacionServicio.conseguirFacturacionPorId(id);
        if (facturacion == null) {
            return ResponseEntity.notFound().build();
        }
        facturacionServicio.borrarFacturacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<EntityModel<Facturacion>>> listarFacturacionesPorUsuario(@PathVariable int usuarioId) {
        List<Facturacion> facturas = facturacionServicio.obtenerFacturacionesPorUsuario(usuarioId);
        if (facturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EntityModel<Facturacion>> recursos = facturas.stream().map(factura -> {
            EntityModel<Facturacion> recurso = EntityModel.of(factura);
            recurso.add(linkTo(methodOn(FacturacionController.class).obtenerFacturacion(factura.getId())).withSelfRel());
            recurso.add(linkTo(methodOn(FacturacionController.class).listarFacturacionesPorUsuario(usuarioId)).withRel("facturas-usuario"));
            recurso.add(linkTo(methodOn(FacturacionController.class).listarFacturaciones()).withRel("todas-facturaciones"));
            return recurso;
        }).toList();

        return ResponseEntity.ok(recursos);
    }

    
}
