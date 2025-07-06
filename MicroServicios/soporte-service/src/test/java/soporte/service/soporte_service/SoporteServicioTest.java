package soporte.service.soporte_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import soporte.service.soporte_service.modelo.Usuario;
import soporte.service.soporte_service.servicio.SoporteServicio;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class SoporteServicioTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SoporteServicio soporteServicio;

    // Test para manejar usuario no encontrado (NotFound)
    @Test
    public void testObtenerUsuarioPorEmail_NoEncontrado() {
        when(restTemplate.getForObject("http://localhost:8001/usuarios/email/noexiste@correo.com", Usuario.class))
            .thenThrow(HttpClientErrorException.create(
                HttpStatus.NOT_FOUND,
                "Not Found",
                HttpHeaders.EMPTY,
                null,
                null
            ));

        Usuario resultado = soporteServicio.obtenerUsuarioPorEmail("noexiste@correo.com");

        assertNull(resultado);
    }

    // Test para manejar error HTTP general (BadRequest)
    @Test
    public void testObtenerUsuarioPorEmail_ErrorHttp() {
        when(restTemplate.getForObject("http://localhost:8001/usuarios/email/error@correo.com", Usuario.class))
            .thenThrow(HttpClientErrorException.create(
                HttpStatus.BAD_REQUEST,
                "Bad Request",
                HttpHeaders.EMPTY,
                null,
                null
            ));

        Usuario resultado = soporteServicio.obtenerUsuarioPorEmail("error@correo.com");

        assertNull(resultado);
    }
}
