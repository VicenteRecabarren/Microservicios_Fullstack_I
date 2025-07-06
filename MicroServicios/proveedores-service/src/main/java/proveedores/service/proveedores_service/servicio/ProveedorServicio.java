package proveedores.service.proveedores_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proveedores.service.proveedores_service.entidades.Proveedor;
import proveedores.service.proveedores_service.repositorio.ProveedorRepository;

@Service
public class ProveedorServicio {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> obtenerTodosLosProveedores(){
        return proveedorRepository.findAll();
    }

    public Proveedor conseguirProveedorPorId(int id){
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor guardarProveedor(Proveedor proveedor){
        Proveedor nuevoProveedor = proveedorRepository.save(proveedor);
        return nuevoProveedor;
    } 

    public void borrarProveedor (int id){
        proveedorRepository.deleteById(id);
    }

}
