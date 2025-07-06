package usuario.service.usuario_service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import usuario.service.usuario_service.Entidades.Usuario;
import usuario.service.usuario_service.Repositorio.UsuarioRepositorio;
import usuario.service.usuario_service.Servicio.UsuarioServicio;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepositorio usuarioRepositorio;
    
    @Mock
    private RestTemplate restTemplate;
    
    @Mock
    private RestTemplate restTemplate2;

    @InjectMocks
    private UsuarioServicio usuarioServicio;

    @Test
    void testObtenerTodos() {
        // Arrange
        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNombre("Usuario Test 1");
        usuario1.setEmail("test1@example.com");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNombre("Usuario Test 2");
        usuario2.setEmail("test2@example.com");

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        
        when(usuarioRepositorio.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> resultado = usuarioServicio.getAll();

        // Assert
        assertEquals(2, resultado.size());
        verify(usuarioRepositorio, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Usuario Test");
        usuario.setEmail("test@example.com");
        
        when(usuarioRepositorio.findById(1)).thenReturn(Optional.of(usuario));

        // Act
        Usuario resultado = usuarioServicio.getUsuarioById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Usuario Test", resultado.getNombre());
        verify(usuarioRepositorio, times(1)).findById(1);
    }
}