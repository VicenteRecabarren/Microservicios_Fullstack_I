package facturacion.service.facturacion_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import facturacion.service.facturacion_service.entidades.Facturacion;
import facturacion.service.facturacion_service.modelo.Usuario;
import facturacion.service.facturacion_service.repositorio.FacturacionRepository;

@Service
public class FacturacionServicio {

    @Autowired
    private FacturacionRepository facturacionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Facturacion> obtenerTodasLasFacturaciones() {
        List<Facturacion> facturas = facturacionRepository.findAll();
        facturas.forEach(factura -> {
            factura.setCliente(obtenerUsuarioPorId(factura.getUsuarioId()));
        });
        return facturas;
    }

    public Usuario obtenerUsuarioPorId(int usuarioId) {
    try {
        String url = "http://localhost:8001/usuarios/" + usuarioId;
        return restTemplate.getForObject(url, Usuario.class);
    } catch (Exception e) {
        System.err.println("Error al obtener usuario: " + e.getMessage());
        return null;
    }
}

    public Facturacion conseguirFacturacionPorId(int id){
        return facturacionRepository.findById(id).orElse(null);
    }

    public Facturacion guardarFacturacion(Facturacion facturacion){
        Facturacion nuevaFacturacion = facturacionRepository.save(facturacion);
        return nuevaFacturacion;
    }

    public void borrarFacturacion(int id) {
        facturacionRepository.deleteById(id);
    }

    public List<Facturacion> obtenerFacturacionesPorUsuario(int usuarioId){
        return facturacionRepository.findByUsuarioId(usuarioId);
    }

}




