package usuario.service.usuario_service.entidades;

import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import usuario.service.usuario_service.modelo.Curso;
import usuario.service.usuario_service.modelo.Rol;

@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String email;
    private String sexo;
    private String direccionPostal;
    private String celular;
    private String empresa;
    private String departamento;
    // que concatené el dia/mes/año de nacimiento

    @ElementCollection
    private List<Integer> cursoIds = new ArrayList<>();

    @Transient
    private Curso curso;


    public List<Integer> getCursoIds(){
        return cursoIds;
    }

    public void setCursoIds(List<Integer> cursoIds){
        this.cursoIds = cursoIds;
    }

    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    private int rolId;

    @Transient
    private Rol rol;

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getDireccionPostal() {
        return direccionPostal;
    }
    public void setDireccionPostal(String direccionPostal) {
        this.direccionPostal = direccionPostal;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    public Usuario(){

    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
