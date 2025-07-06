package curso.service.curso_service.modelo;

public class Proveedor {
    private int id;
    private String nombreProveedor;
    private String tipoProveedor;
    private String email;
    private String especialidad;
    private String descripcion;
    private boolean estadoProveedor;

    // Constructor vacío
    public Proveedor() {
    }

    // Getters y Setters
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
}
