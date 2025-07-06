package rol.service.rol_service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rol.service.rol_service.entidades.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

}
