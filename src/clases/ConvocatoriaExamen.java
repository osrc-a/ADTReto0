package clases;

import java.sql.Date;
import java.time.LocalDate;

public class ConvocatoriaExamen {

    // Atributos
    private int id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private String convocatoria;
    private String descripcion;
    private LocalDate fecha;
    private String curso;

    // Constructores
    public ConvocatoriaExamen(Integer Id, String convocatoria, String descripcion, LocalDate fecha, String curso) {
        this.id = id;
        this.convocatoria = convocatoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.curso = curso;
    }

    public ConvocatoriaExamen() {
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

}
