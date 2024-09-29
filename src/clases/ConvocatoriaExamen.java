package clases;

import java.sql.Date;
import java.time.LocalDate;

public class ConvocatoriaExamen {

    // Atributos
    private int id;
    private String convocatoria;
    private String descripcion;
    private LocalDate fecha;
    private String curso;

    // Constructores
    public ConvocatoriaExamen(int id, String convocatoria, String descripcion, LocalDate fecha, String curso) {
        this.id = id;
        this.convocatoria = convocatoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.curso = curso;
    }

    public ConvocatoriaExamen() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters y setters 
    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "ConvocatoriaExamen {"
                + "\n  ID            : " + id
                + "\n  Convocatoria  : " + convocatoria
                + "\n  Descripción   : " + descripcion
                + "\n  Fecha         : " + fecha
                + "\n  Curso         : " + curso
                + "\n}";
    }

}
