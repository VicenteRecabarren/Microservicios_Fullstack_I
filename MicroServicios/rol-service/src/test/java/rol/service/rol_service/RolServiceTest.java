package rol.service.rol_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import rol.service.rol_service.Entidades.Rol;
import rol.service.rol_service.Repositorio.RolRepositorio;
import rol.service.rol_service.Servicio.RolServicio;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RolServiceTest {

    @MockBean
    private RolRepositorio rolRepositorio;

    @Autowired
    private RolServicio rolServicio;

    @Test
    void testObtenerTodos() {

        Rol rol1 = new Rol();
        rol1.setId(1);
        rol1.setNombre("ADMIN");
        rol1.setUsuarioId(1);

        Rol rol2 = new Rol();
        rol2.setId(2);
        rol2.setNombre("USER");
        rol2.setUsuarioId(2);

        List<Rol> roles = Arrays.asList(rol1, rol2);
        
        when(rolRepositorio.findAll()).thenReturn(roles);

        List<Rol> resultado = rolServicio.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(rolRepositorio, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {

        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombre("ADMIN");
        rol.setUsuarioId(1);
        
        when(rolRepositorio.findById(1)).thenReturn(Optional.of(rol));

        Rol resultado = rolServicio.obtenerPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("ADMIN", resultado.getNombre());
        verify(rolRepositorio, times(1)).findById(1);
    }
}