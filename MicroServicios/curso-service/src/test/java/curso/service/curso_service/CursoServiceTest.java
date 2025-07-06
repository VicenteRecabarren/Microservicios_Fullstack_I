package curso.service.curso_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import curso.service.curso_service.entidades.Curso;
import curso.service.curso_service.modelo.Proveedor;
import curso.service.curso_service.repositorio.CursoRepository;
import curso.service.curso_service.servicio.CursoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CursoService cursoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodosLosCursos() {
        Curso c1 = new Curso();
        c1.setId(1);
        c1.setTitulo("Historia de Chile");

        Curso c2 = new Curso();
        c2.setId(2);
        c2.setTitulo("Matemática Avanzada");

        when(cursoRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Curso> resultado = cursoService.obtenerTodosLosCursos();

        assertEquals(2, resultado.size());
        assertEquals("Matemática Avanzada", resultado.get(1).getTitulo());
    }

    @Test
    public void testConseguirCursoPorId_Existe() {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setTitulo("Lógica");

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        Curso resultado = cursoService.conseguirCursoPorId(1);

        assertNotNull(resultado);
        assertEquals("Lógica", resultado.getTitulo());
    }

    @Test
    public void testConseguirCursoPorId_NoExiste() {
        when(cursoRepository.findById(99)).thenReturn(Optional.empty());

        Curso resultado = cursoService.conseguirCursoPorId(99);

        assertNull(resultado);
    }

    @Test
    public void testGuardarCurso() {
        Curso curso = new Curso();
        curso.setTitulo("Arte y Cultura");

        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso resultado = cursoService.guardarCurso(curso);

        assertEquals("Arte y Cultura", resultado.getTitulo());
    }

    @Test
    public void testObtenerCursoConProveedor() {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setTitulo("Física");
        curso.setProveedorId(123);

        Proveedor proveedor = new Proveedor();
        proveedor.setId(123);
        proveedor.setNombreProveedor("Ministerio de Educación");

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
        when(restTemplate.getForObject("http://localhost:8004/proveedores/123", Proveedor.class)).thenReturn(proveedor);

        Curso resultado = cursoService.obtenerCursoConProveedor(1);

        assertNotNull(resultado.getProveedor());
        assertEquals("Ministerio de Educación", resultado.getProveedor().getNombreProveedor());
    }
}
