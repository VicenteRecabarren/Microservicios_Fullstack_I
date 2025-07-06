package soporte.service.soporte_service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soporte.service.soporte_service.entidades.Soporte;

@Repository
public interface SoporteRepository extends JpaRepository<Soporte,Integer>{

}
