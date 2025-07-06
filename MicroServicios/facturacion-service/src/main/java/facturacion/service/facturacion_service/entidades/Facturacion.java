package facturacion.service.facturacion_service.entidades;

import jakarta.persistence.Transient;
import facturacion.service.facturacion_service.modelo.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Facturacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int usuarioId;

    private String nombreCliente;      // Nombre o identificación del cliente
    private int monto;
    private String fechaEmision;
    private String estadoPago;
    private String metodoPago;

    @Transient
    private Usuario cliente;

    public Facturacion() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstadoPago() {
        return estadoPago;
    }
    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    // ✅ Cliente como objeto Usuario
    public Usuario getCliente() {
        return cliente;
    }
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }
}
