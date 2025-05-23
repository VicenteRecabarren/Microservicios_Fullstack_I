package alerta.service.alerta_service.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alerta.service.alerta_service.Entidades.Alerta;

@Repository
public interface AlertaRepositorio extends JpaRepository<Alerta, Integer>{
    List<Alerta> findByUsuarioid(int usuarioid);
}
