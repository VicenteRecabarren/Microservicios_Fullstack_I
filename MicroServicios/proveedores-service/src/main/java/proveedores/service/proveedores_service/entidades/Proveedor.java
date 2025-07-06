package proveedores.service.proveedores_service.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreProveedor;
    private String tipoProveedor; // Institución / Independiente / Empresa
    private String email;
    private String especialidad; // Especialidad del proveedor
    private String descripcion; // Descripción acerca del proveedor
    private boolean estadoProveedor; // proveedor en servicio o sin servicio - true/false
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreProveedor() {
        return nombreProveedor;
    }
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
    public String getTipoProveedor() {
        return tipoProveedor;
    }
    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public boolean isEstadoProveedor() {
        return estadoProveedor;
    }
    public void setEstadoProveedor(boolean estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }

    public Proveedor(){
        
    }

}

