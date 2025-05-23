package rol.service.rol_service.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rol.service.rol_service.Entidades.Rol;
import rol.service.rol_service.Repositorio.RolRepositorio;

@Service
public class RolServicio {

    @Autowired
    private RolRepositorio rolRepositorio;
    public List<Rol>getAll(){
        return rolRepositorio.findAll();
    }

    public Rol getRolById(int id){
        return rolRepositorio.findById(id).orElse(null);
    }

    public Rol save(Rol rol){
        Rol nuevoRol = rolRepositorio.save(rol);
        return nuevoRol;
    }

    public List<Rol> byUsuarioId(int usuarioid){
        return rolRepositorio.findByUsuarioId(usuarioid);
    }

}
