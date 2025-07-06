package rol.service.rol_service.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreRol;
    private String nivelPermisos; // bajo - medio - alto
    private String descripcion; // "Permite acceso de solo lectura a los cursos"
    private boolean estadoRol; // activo - desactivado

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreRol() {
        return nombreRol;
    }
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    public String getNivelPermisos() {
        return nivelPermisos;
    }
    public void setNivelPermisos(String nivelPermisos) {
        this.nivelPermisos = nivelPermisos;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public boolean isEstadoRol() {
        return estadoRol;
    }
    public void setEstadoRol(boolean estadoRol) {
        this.estadoRol = estadoRol;
    }

    public Rol(){
        
    }

}
