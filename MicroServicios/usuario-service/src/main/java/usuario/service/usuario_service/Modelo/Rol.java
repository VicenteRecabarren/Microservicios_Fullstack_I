package usuario.service.usuario_service.modelo;

public class Rol {
    private int id;
    private String nombreRol;
    private String nivelPermisos;
    private String descripcion;
    private boolean estadoRol;

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

    public Rol() {
    }
}
