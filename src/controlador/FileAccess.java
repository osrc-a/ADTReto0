/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.ConvocatoriaExamen;
import clases.Enunciado;
import clases.UnidadDidactica;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class FileAccess implements AccessManager {

    @Override
    public List<ConvocatoriaExamen> getExamenConEnunciadoConcreto(int idEnunciado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRutaEnunciado(int idEnunciado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void asignarEnunciadoAConvocatoria(int idConvocatoria, int idEnunciado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearUnidadDactica(UnidadDidactica uniDidac) {
        Properties properties = new Properties();

        // Cargar los datos del objeto
        properties.setProperty("id", String.valueOf(uniDidac.getId()));
        properties.setProperty("acronimo", uniDidac.getAcronimo());
        properties.setProperty("titulo", uniDidac.getTitulo());
        properties.setProperty("evaluacion", uniDidac.getEvaluacion());
        properties.setProperty("descripcion", uniDidac.getDescripcion());

        // Escribir los datos en el archivo de propiedades
        try (FileOutputStream output = new FileOutputStream("controlador.CrearUnidadConvocatoria.properties")) {
            properties.store(output, "Datos de Unidad Didactica");
            System.out.println("Los datos de UnidadDidactica fueron guardados en el archivo con éxito.");
        } catch (IOException e) {
            Logger.getLogger("controlador").severe(e.getLocalizedMessage());
        }
    }

    @Override
    public void crearConvocatoria(ConvocatoriaExamen convoExam) {
        Properties properties = new Properties();

        // Cargar los datos del objeto
        properties.setProperty("id", String.valueOf(convoExam.getId()));
        properties.setProperty("convocatoria", convoExam.getConvocatoria());
        properties.setProperty("descripcion", convoExam.getDescripcion());
        properties.setProperty("fecha", convoExam.getFecha().toString()); // Asegúrate de que sea un tipo de dato compatible
        properties.setProperty("curso", convoExam.getCurso());

        // Escribir los datos en el archivo de propiedades
        try (FileOutputStream output = new FileOutputStream("controlador.CrearUnidadConvocatoria.properties")) {
            properties.store(output, "Datos de Convocatoria de Examen");
            System.out.println("Los datos de ConvocatoriaExamen fueron guardados en el archivo con éxito.");
        } catch (IOException e) {
            Logger.getLogger("controlador").severe(e.getLocalizedMessage());
        }
    }

    @Override
    public void crearEnunciadoDeUnidadConvocatoria(Enunciado enunciado, int idUnidadDidactica, int idConvocatoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Enunciado> getEnunciadoUnidad(int idUnidadDidactica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnidadDidactica> getUnidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ConvocatoriaExamen> getConvocatoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Enunciado> getEnunciado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
