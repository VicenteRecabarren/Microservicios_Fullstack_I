package usuario.service.usuario_service.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import usuario.service.usuario_service.entidades.Usuario;
import usuario.service.usuario_service.modelo.Curso;
import usuario.service.usuario_service.modelo.Rol;
import usuario.service.usuario_service.repositorio.UsuarioRepository;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Usuario> obtenerTodosLosUsuarios(){
        return usuarioRepository.findAll();
    }
    
    public Usuario conseguirUsuarioPorId(int id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null && usuario.getRolId() != 0){
            Rol rol = obtenerRolDeUsuario(usuario.getRolId());
            usuario.setRol(rol);
        }
        return usuario;
    }
    
    public Usuario guardarUsuario(Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }
    
    public void borrarUsuario (int id){
        usuarioRepository.deleteById(id);
    }

    public Rol obtenerRolDeUsuario(int rolId){
        String rolUrl = "http://localhost:8002/roles/" + rolId;
        Rol rol = restTemplate.getForObject(rolUrl, Rol.class);
        return rol;
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Curso obtenerCursoPorId(int cursoId){
        try {
            String url = "http://localhost:8003/cursos/" + cursoId;
            return restTemplate.getForObject(url, Curso.class);
        } catch (Exception e) {
            System.err.println("Error al obtener curso: "+ e.getMessage());
            return null;
        }
    }

    public List<Curso> obtenerCursosDeUsuario(int usuarioId) {
    Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return null;
        }
        List<Curso> cursos = new ArrayList<>();
        for (Integer idCurso : usuario.getCursoIds()) {
            Curso curso = obtenerCursoPorId(idCurso);
            if (curso != null) {
                cursos.add(curso);
            }
        }
        return cursos;
    }

    public List<Usuario> obtenerUsuariosPorCurso(int cursoId) {
    List<Usuario> todosLosUsuarios = usuarioRepository.findAll();
    List<Usuario> usuariosEnCurso = new ArrayList<>();
        for (Usuario usuario : todosLosUsuarios) {
            List<Integer> cursoIds = usuario.getCursoIds();
            if (cursoIds != null && cursoIds.contains(cursoId)) {
                usuario.setCurso(obtenerCursoPorId(cursoId));
                usuariosEnCurso.add(usuario);
            }
        }
        return usuariosEnCurso;
    }

    public Usuario asignarCursoUsuario(int usuarioId, int cursoId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null){
            return null;
        }
        List<Integer> cursos = usuario.getCursoIds();
        if (!cursos.contains(cursoId)) {
            cursos.add(cursoId);
            usuario.setCursoIds(cursos);
            usuarioRepository.save(usuario);
        }
        return usuario;
    }

}
