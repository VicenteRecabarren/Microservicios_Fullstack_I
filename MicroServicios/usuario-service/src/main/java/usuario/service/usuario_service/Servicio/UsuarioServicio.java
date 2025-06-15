package usuario.service.usuario_service.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import usuario.service.usuario_service.Entidades.Usuario;
import usuario.service.usuario_service.Modelo.Curso;
import usuario.service.usuario_service.Modelo.Rol;
import usuario.service.usuario_service.Repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private RestTemplate restTemplate;

    public List<Rol> getRoles(int usuarioId){
        @SuppressWarnings("unchecked")
        List<Rol> roles = restTemplate.getForObject("http://localhost:8002/rol/usuario/"+usuarioId, List.class);
        return roles;
    }

    @Autowired
    private RestTemplate restTemplate2;

    public List<Curso> getCursos(int usuarioId){
        @SuppressWarnings("unchecked")
        List<Curso> cursos = restTemplate2.getForObject("http://localhost:8003/curso/usuario/"+usuarioId, List.class);
        return cursos;
    }
    
    public List<Usuario> obtenerTodos() {
        return getAll();
    }
    
    public Usuario obtenerPorId(int id) {
        return getUsuarioById(id);
    }

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    public List<Usuario>getAll(){
        return usuarioRepositorio.findAll();
    }

    public Usuario getUsuarioById(int id){
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario nuevoUsuario = usuarioRepositorio.save(usuario);
        return nuevoUsuario;
    }

    public void deleteUser (int id){
        usuarioRepositorio.deleteById(id);
    }
}
