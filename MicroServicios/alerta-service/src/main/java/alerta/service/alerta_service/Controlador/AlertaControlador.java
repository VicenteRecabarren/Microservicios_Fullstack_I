package alerta.service.alerta_service.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alerta.service.alerta_service.Entidades.Alerta;
import alerta.service.alerta_service.Servicio.AlertaServicio;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/alerta")
public class AlertaControlador {

    @Autowired
    private AlertaServicio alertaServicio;

    @GetMapping
    public ResponseEntity<List<Alerta>> listarAlerta(){
        List<Alerta> alerta = alertaServicio.getAll();
        if(alerta.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alerta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alerta> obtenerAlerta(@PathVariable("id") int id) {
        Alerta alerta = alertaServicio.getCursoById(id);
        if (alerta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alerta);
    }

    @PostMapping
    public ResponseEntity<Alerta> guardarAlerta(@RequestBody Alerta alerta) {
        Alerta nuevaAlerta = alertaServicio.save(alerta);
        return ResponseEntity.ok(nuevaAlerta);
    }

    @GetMapping("/usuario/{usuarioid}")
    public ResponseEntity<List<Alerta>> listarAlertasPorUsuarioId(@PathVariable("usuarioid")int id) {
        List<Alerta> alertas = alertaServicio.byUsuarioId(id);
        if(alertas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alertas);
    }

    @PutMapping("/asignar/{alertaId}/{usuarioId}")
    public ResponseEntity<Alerta> AsignarAlertaUsuario(
        @PathVariable int alertaId,
        @PathVariable int usuarioId) {
        
        Alerta alerta = alertaServicio.getCursoById(alertaId);
        if(alerta == null){
            return ResponseEntity.notFound().build();
        }
        alerta.setUsuarioid(usuarioId);
        Alerta putAlerta = alertaServicio.save(alerta);
        return ResponseEntity.ok(putAlerta);
    }
    

}
