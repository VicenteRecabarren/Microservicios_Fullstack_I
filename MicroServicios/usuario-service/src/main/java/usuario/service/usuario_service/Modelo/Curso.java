package usuario.service.usuario_service.modelo;

public class Curso {
    private int id;
    private String titulo;
    private String nivel;
    private int duracionHoras;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getNivel() {
        return nivel;
    }
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    public int getDuracionHoras() {
        return duracionHoras;
    }
    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }
    public Curso() {
    }

}

