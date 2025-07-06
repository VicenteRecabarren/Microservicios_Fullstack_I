package facturacion.service.facturacion_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import facturacion.service.facturacion_service.entidades.Facturacion;
import facturacion.service.facturacion_service.modelo.Usuario;
import facturacion.service.facturacion_service.repositorio.FacturacionRepository;
import facturacion.service.facturacion_service.servicio.FacturacionServicio;

public class FacturacionServicioTest {

    @Mock
    private FacturacionRepository facturacionRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FacturacionServicio facturacionServicio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodasLasFacturaciones() {
        Facturacion f1 = new Facturacion();
        f1.setId(1);
        f1.setUsuarioId(101);
        f1.setMonto(5000);

        Facturacion f2 = new Facturacion();
        f2.setId(2);
        f2.setUsuarioId(102);
        f2.setMonto(7000);

        when(facturacionRepository.findAll()).thenReturn(Arrays.asList(f1, f2));
        when(restTemplate.getForObject("http://localhost:8001/usuarios/101", Usuario.class))
            .thenReturn(new Usuario());
        when(restTemplate.getForObject("http://localhost:8001/usuarios/102", Usuario.class))
            .thenReturn(new Usuario());

        List<Facturacion> resultado = facturacionServicio.obtenerTodasLasFacturaciones();

        assertEquals(2, resultado.size());
        assertNotNull(resultado.get(0).getCliente());
        assertNotNull(resultado.get(1).getCliente());
    }

    @Test
    public void testConseguirFacturacionPorId_Existe() {
        Facturacion f = new Facturacion();
        f.setId(1);
        f.setMonto(4500);

        when(facturacionRepository.findById(1)).thenReturn(Optional.of(f));

        Facturacion resultado = facturacionServicio.conseguirFacturacionPorId(1);

        assertNotNull(resultado);
        assertEquals(4500, resultado.getMonto());
    }

    @Test
    public void testConseguirFacturacionPorId_NoExiste() {
        when(facturacionRepository.findById(99)).thenReturn(Optional.empty());

        Facturacion resultado = facturacionServicio.conseguirFacturacionPorId(99);

        assertNull(resultado);
    }

    @Test
    public void testGuardarFacturacion() {
        Facturacion f = new Facturacion();
        f.setMonto(8000);
        f.setUsuarioId(103);

        when(facturacionRepository.save(f)).thenReturn(f);

        Facturacion resultado = facturacionServicio.guardarFacturacion(f);

        assertEquals(8000, resultado.getMonto());
        assertEquals(103, resultado.getUsuarioId());
    }

    @Test
    public void testBorrarFacturacion() {
        int id = 5;
        facturacionServicio.borrarFacturacion(id);
        verify(facturacionRepository, times(1)).deleteById(id);
    }

    @Test
    public void testObtenerFacturacionesPorUsuario() {
        Facturacion f1 = new Facturacion();
        f1.setUsuarioId(10);
        Facturacion f2 = new Facturacion();
        f2.setUsuarioId(10);

        when(facturacionRepository.findByUsuarioId(10)).thenReturn(Arrays.asList(f1, f2));

        List<Facturacion> resultado = facturacionServicio.obtenerFacturacionesPorUsuario(10);

        assertEquals(2, resultado.size());
        assertEquals(10, resultado.get(0).getUsuarioId());
    }
}
