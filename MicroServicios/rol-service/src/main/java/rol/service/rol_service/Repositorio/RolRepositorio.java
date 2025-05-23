package rol.service.rol_service.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rol.service.rol_service.Entidades.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Integer> {
    List<Rol> findByUsuarioId(int usuarioId);
}
