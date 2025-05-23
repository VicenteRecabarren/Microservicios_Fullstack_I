package alerta.service.alerta_service.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Alerta {
    private int id;
    private String codAlerta;
    private String descripcionAlerta;
    private int usuarioid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCodAlerta() {
        return codAlerta;
    }
    public void setCodAlerta(String codAlerta) {
        this.codAlerta = codAlerta;
    }
    public String getDescripcionAlerta() {
        return descripcionAlerta;
    }
    public void setDescripcionAlerta(String descripcionAlerta) {
        this.descripcionAlerta = descripcionAlerta;
    }
    public int getUsuarioid() {
        return usuarioid;
    }
    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public Alerta(){
        
    }

}
