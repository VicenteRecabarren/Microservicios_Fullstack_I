package curso.service.curso_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import curso.service.curso_service.entidades.Curso;
import curso.service.curso_service.modelo.Proveedor;
import curso.service.curso_service.repositorio.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Curso> obtenerTodosLosCursos(){
        return cursoRepository.findAll();
    }

    public Curso conseguirCursoPorId(int id){
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso guardarCurso(Curso curso){
        Curso nuevoCurso = cursoRepository.save(curso);
        return nuevoCurso;
    }

    public void borrarCurso (int id){
        cursoRepository.deleteById(id);
    }

    public Proveedor obtenerProveedorPorId(int proveedorId) {
        try {
            String url = "http://localhost:8004/proveedores/" + proveedorId;
            return restTemplate.getForObject(url, Proveedor.class);
        } catch (Exception e) {
            System.err.println("Error al obtener proveedor: " + e.getMessage());
            return null;
        }
    }

    public Curso obtenerCursoConProveedor(int id) {
    Curso curso = cursoRepository.findById(id).orElse(null);
    if (curso != null && curso.getProveedorId() != 0) {
        Proveedor proveedor = obtenerProveedorPorId(curso.getProveedorId());
        curso.setProveedor(proveedor);
    }
    return curso;
}
}
