package clases;

public class Enunciado {

    // Atributos
    private int id;
    private String descripcion;
    private Dificultad nivel;
    private boolean disponible;
    private String ruta;

    // Constructor
    public Enunciado(int id, String descripcion, Dificultad nivel, boolean disponible, String ruta) {
        this.id = id;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.disponible = disponible;
        this.ruta = ruta;
    }

    public Enunciado() {
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Dificultad getNivel() {
        return nivel;
    }

    public void setNivel(Dificultad nivel) {
        this.nivel = nivel;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Enunciado {"
                + "\n  ID          : " + id
                + "\n  Descripción : " + descripcion
                + "\n  Nivel       : " + nivel
                + "\n  Disponible  : " + (disponible ? "Sí" : "No")
                + "\n  Ruta        : " + ruta
                + "\n}";
    }

}
