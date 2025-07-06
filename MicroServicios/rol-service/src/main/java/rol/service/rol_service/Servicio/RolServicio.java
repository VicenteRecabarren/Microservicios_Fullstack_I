package rol.service.rol_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rol.service.rol_service.entidades.Rol;
import rol.service.rol_service.repositorio.RolRepository;

@Service
public class RolServicio {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> obtenerTodosLosRoles(){
        return rolRepository.findAll();
    }

    public Rol conseguirRolPorId(int id){
        return rolRepository.findById(id).orElse(null);
    }

    public Rol guardarRol(Rol rol){
        Rol nuevoRol = rolRepository.save(rol);
        return nuevoRol;
    }

    public void borrarRol (int id){
        rolRepository.deleteById(id);
    }

}
