package rol.service.rol_service;

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

import rol.service.rol_service.entidades.Rol;
import rol.service.rol_service.repositorio.RolRepository;
import rol.service.rol_service.servicio.RolServicio;

public class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolServicio rolServicio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodosLosRoles() {
        Rol r1 = new Rol();
        r1.setId(1);
        r1.setNombreRol("Estudiante");

        Rol r2 = new Rol();
        r2.setId(2);
        r2.setNombreRol("Profesor");

        when(rolRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Rol> resultado = rolServicio.obtenerTodosLosRoles();

        assertEquals(2, resultado.size());
        assertEquals("Profesor", resultado.get(1).getNombreRol());
    }

    @Test
    public void testConseguirRolPorId_Existe() {
        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombreRol("Administrador");

        when(rolRepository.findById(1)).thenReturn(Optional.of(rol));

        Rol resultado = rolServicio.conseguirRolPorId(1);

        assertNotNull(resultado);
        assertEquals("Administrador", resultado.getNombreRol());
    }

    @Test
    public void testConseguirRolPorId_NoExiste() {
        when(rolRepository.findById(99)).thenReturn(Optional.empty());

        Rol resultado = rolServicio.conseguirRolPorId(99);

        assertNull(resultado);
    }

    @Test
    public void testGuardarRol() {
        Rol rol = new Rol();
        rol.setNombreRol("Invitado");

        when(rolRepository.save(rol)).thenReturn(rol);

        Rol resultado = rolServicio.guardarRol(rol);

        assertNotNull(resultado);
        assertEquals("Invitado", resultado.getNombreRol());
    }

    @Test
    public void testBorrarRol() {
        int rolId = 5;

        rolServicio.borrarRol(rolId);

        verify(rolRepository, times(1)).deleteById(rolId);
    }
}
