package usuario.service.usuario_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import usuario.service.usuario_service.entidades.Usuario;
import usuario.service.usuario_service.repositorio.UsuarioRepository;
import usuario.service.usuario_service.servicio.UsuarioServicio;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServicio usuarioServicio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodosLosUsuarios() {
        Usuario u1 = new Usuario();
        u1.setId(1);
        u1.setNombre("Usuario Uno");

        Usuario u2 = new Usuario();
        u2.setId(2);
        u2.setNombre("Usuario Dos");

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Usuario> resultado = usuarioServicio.obtenerTodosLosUsuarios();

        assertEquals(2, resultado.size());
        assertEquals("Usuario Uno", resultado.get(0).getNombre());
    }

    @Test
    public void testConseguirUsuarioPorId_Existe() {
        Usuario u = new Usuario();
        u.setId(1);
        u.setNombre("Test Usuario");
        u.setRolId(0); // Para evitar llamada a restTemplate

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(u));

        Usuario resultado = usuarioServicio.conseguirUsuarioPorId(1);

        assertNotNull(resultado);
        assertEquals("Test Usuario", resultado.getNombre());
    }

    @Test
    public void testConseguirUsuarioPorId_NoExiste() {
        when(usuarioRepository.findById(999)).thenReturn(Optional.empty());

        Usuario resultado = usuarioServicio.conseguirUsuarioPorId(999);

        assertNull(resultado);
    }

    @Test
    public void testGuardarUsuario() {
        Usuario u = new Usuario();
        u.setNombre("Guardar Usuario");

        when(usuarioRepository.save(u)).thenReturn(u);

        Usuario resultado = usuarioServicio.guardarUsuario(u);

        assertNotNull(resultado);
        assertEquals("Guardar Usuario", resultado.getNombre());
    }

    @Test
    public void testBorrarUsuario() {
        int id = 10;

        usuarioServicio.borrarUsuario(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }
} 
