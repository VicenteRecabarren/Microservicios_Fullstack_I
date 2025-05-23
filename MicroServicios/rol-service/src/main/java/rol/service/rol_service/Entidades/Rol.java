package rol.service.rol_service.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rol {
    private int id;
    private String nombre;
    private int usuarioid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getUsuarioId(){
        return usuarioid;
    }

    public void setUsuarioId(int usuarioid){
        this.usuarioid = usuarioid;
    }

    public Rol(){
        
    }

}
