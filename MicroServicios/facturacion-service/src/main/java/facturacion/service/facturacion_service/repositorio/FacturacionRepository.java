package facturacion.service.facturacion_service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import facturacion.service.facturacion_service.entidades.Facturacion;

@Repository
public interface FacturacionRepository extends JpaRepository<Facturacion, Integer> {
    List<Facturacion> findByUsuarioId(int usuarioId);
}