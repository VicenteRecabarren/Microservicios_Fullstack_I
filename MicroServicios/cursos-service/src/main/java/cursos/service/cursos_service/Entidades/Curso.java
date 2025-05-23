package cursos.service.cursos_service.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Curso {
    private int id;
    private String nombre;
    private String descripcion;
    private String seccion;
    private int usuarioid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getSeccion() {
        return seccion;
    }
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    public int getUsuarioid() {
        return usuarioid;
    }
    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public Curso(){
        
    }

}
