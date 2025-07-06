package alerta.service.alerta_service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import alerta.service.alerta_service.Entidades.Alerta;
import alerta.service.alerta_service.Repositorio.AlertaRepositorio;
import alerta.service.alerta_service.Servicio.AlertaServicio;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlertaServiceTest {

    @Mock
    private AlertaRepositorio alertaRepositorio;

    @InjectMocks
    private AlertaServicio alertaServicio;

    @Test
    void testObtenerTodos() {
        // Arrange
        Alerta alerta1 = new Alerta();
        alerta1.setId(1);
        alerta1.setCodAlerta("ALT001");
        alerta1.setDescripcionAlerta("Alerta de prueba 1");
        alerta1.setUsuarioid(1);

        Alerta alerta2 = new Alerta();
        alerta2.setId(2);
        alerta2.setCodAlerta("ALT002");
        alerta2.setDescripcionAlerta("Alerta de prueba 2");
        alerta2.setUsuarioid(2);

        List<Alerta> alertas = Arrays.asList(alerta1, alerta2);
        
        when(alertaRepositorio.findAll()).thenReturn(alertas);

        // Act
        List<Alerta> resultado = alertaServicio.obtenerTodos();

        // Assert
        assertEquals(2, resultado.size());
        verify(alertaRepositorio, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        // Arrange
        Alerta alerta = new Alerta();
        alerta.setId(1);
        alerta.setCodAlerta("ALT001");
        alerta.setDescripcionAlerta("Alerta de prueba");
        alerta.setUsuarioid(1);
        
        when(alertaRepositorio.findById(1)).thenReturn(Optional.of(alerta));

        // Act
        Alerta resultado = alertaServicio.obtenerPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("ALT001", resultado.getCodAlerta());
        verify(alertaRepositorio, times(1)).findById(1);
    }
}