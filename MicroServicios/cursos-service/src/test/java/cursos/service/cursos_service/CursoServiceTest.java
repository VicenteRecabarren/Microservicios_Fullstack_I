package cursos.service.cursos_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import cursos.service.cursos_service.Entidades.Curso;
import cursos.service.cursos_service.Repositorio.CursoRepositorio;
import cursos.service.cursos_service.Servicio.CursoServicio;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CursoServiceTest {

    @MockBean
    private CursoRepositorio cursoRepositorio;

    @Autowired
    private CursoServicio cursoServicio;

    @Test
    void testObtenerTodos() {
        // Arrange
        Curso curso1 = new Curso();
        curso1.setId(1);
        curso1.setNombre("Java Básico");
        curso1.setDescripcion("Curso básico de Java");
        curso1.setSeccion("Programación");
        curso1.setUsuarioid(1);

        Curso curso2 = new Curso();
        curso2.setId(2);
        curso2.setNombre("Spring Boot");
        curso2.setDescripcion("Curso de Spring Boot");
        curso2.setSeccion("Frameworks");
        curso2.setUsuarioid(1);

        List<Curso> cursos = Arrays.asList(curso1, curso2);
        
        when(cursoRepositorio.findAll()).thenReturn(cursos);

        // Act
        List<Curso> resultado = cursoServicio.obtenerTodos();

        // Assert
        assertEquals(2, resultado.size());
        verify(cursoRepositorio, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        // Arrange
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Java Básico");
        curso.setDescripcion("Curso básico de Java");
        curso.setSeccion("Programación");
        curso.setUsuarioid(1);
        
        when(cursoRepositorio.findById(1)).thenReturn(Optional.of(curso));

        // Act
        Curso resultado = cursoServicio.obtenerPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Java Básico", resultado.getNombre());
        verify(cursoRepositorio, times(1)).findById(1);
    }
}