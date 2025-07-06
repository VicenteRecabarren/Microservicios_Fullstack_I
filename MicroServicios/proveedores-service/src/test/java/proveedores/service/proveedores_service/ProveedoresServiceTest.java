package proveedores.service.proveedores_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import proveedores.service.proveedores_service.entidades.Proveedor;
import proveedores.service.proveedores_service.repositorio.ProveedorRepository;
import proveedores.service.proveedores_service.servicio.ProveedorServicio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProveedoresServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServicio proveedorServicio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodosLosProveedores() {
        Proveedor p1 = new Proveedor();
        p1.setId(1);
        p1.setNombreProveedor("Wikipedia");

        Proveedor p2 = new Proveedor();
        p2.setId(2);
        p2.setNombreProveedor("Ministerio de Educación");

        when(proveedorRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Proveedor> resultado = proveedorServicio.obtenerTodosLosProveedores();

        assertEquals(2, resultado.size());
        assertEquals("Ministerio de Educación", resultado.get(1).getNombreProveedor());
    }

    @Test
    public void testConseguirProveedorPorId_Existe() {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(1);
        proveedor.setNombreProveedor("Duoc UC");

        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));

        Proveedor resultado = proveedorServicio.conseguirProveedorPorId(1);

        assertNotNull(resultado);
        assertEquals("Duoc UC", resultado.getNombreProveedor());
    }

    @Test
    public void testConseguirProveedorPorId_NoExiste() {
        when(proveedorRepository.findById(99)).thenReturn(Optional.empty());

        Proveedor resultado = proveedorServicio.conseguirProveedorPorId(99);

        assertNull(resultado);
    }

    @Test
    public void testGuardarProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombreProveedor("UNESCO");

        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        Proveedor resultado = proveedorServicio.guardarProveedor(proveedor);

        assertEquals("UNESCO", resultado.getNombreProveedor());
    }

    @Test
    public void testBorrarProveedor() {
        int id = 10;
        proveedorServicio.borrarProveedor(id);

        verify(proveedorRepository, times(1)).deleteById(id);
    }
}