package soporte.service.soporte_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import soporte.service.soporte_service.entidades.Soporte;
import soporte.service.soporte_service.modelo.Usuario;
import soporte.service.soporte_service.repositorio.SoporteRepository;

@Service
public class SoporteServicio {

    @Autowired
    private SoporteRepository soporteRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Soporte> obtenerTodosLosSoportes(){
        return soporteRepository.findAll();
    }

    public Soporte conseguirSoportePorId(int id){
        return soporteRepository.findById(id).orElse(null);
    }

    public Soporte guardarSoporte(Soporte soporte){
        Soporte nuevoSoporte = soporteRepository.save(soporte);
        return nuevoSoporte;
    }

    public void borrarSoporte(int id){
        soporteRepository.deleteById(id);
    }

    public Usuario obtenerUsuarioPorEmail(String email) {
    String url = "http://localhost:8001/usuarios/email/" + email;
    try {
        return restTemplate.getForObject(url, Usuario.class);
    } catch (HttpClientErrorException.NotFound e) {
        // Usuario no encontrado
        return null;
    } catch (HttpClientErrorException e) {
        // Otros errores HTTP
        System.err.println("Error al obtener usuario: " + e.getStatusCode());
        return null;
    }
}



}
