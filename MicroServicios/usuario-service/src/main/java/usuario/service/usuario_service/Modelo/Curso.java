package usuario.service.usuario_service.Modelo;

public class Curso {
    public String nombre;
    public String descripcion;
    public String seccion;

    public Curso(){
        
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
}
