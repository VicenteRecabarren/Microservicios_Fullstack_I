package proveedores.service.proveedores_service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proveedores.service.proveedores_service.entidades.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{

}
