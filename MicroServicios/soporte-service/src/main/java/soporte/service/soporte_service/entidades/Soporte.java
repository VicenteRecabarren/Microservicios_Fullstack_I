package soporte.service.soporte_service.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Soporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tipoSoporte;       // Técnico / Académico / Administrativo
    private String estadoSoporte;     // Pendiente / En Proceso / Resuelto
    private String descripcion;       // Detalle del problema o solicitud
    private String emailContacto;     // Email del usuario solicitante
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipoSoporte() {
        return tipoSoporte;
    }
    public void setTipoSoporte(String tipoSoporte) {
        this.tipoSoporte = tipoSoporte;
    }
    public String getEstadoSoporte() {
        return estadoSoporte;
    }
    public void setEstadoSoporte(String estadoSoporte) {
        this.estadoSoporte = estadoSoporte;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getEmailContacto() {
        return emailContacto;
    }
    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public Soporte(){
        
    }

}
