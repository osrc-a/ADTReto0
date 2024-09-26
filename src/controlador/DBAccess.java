/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.ConvocatoriaExamen;
import clases.Enunciado;
import clases.UnidadDidactica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class DBAccess implements AccessManager{
    
        private Connection conectar() throws SQLException {
        // Configura la conexión a la base de datos
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_adt", "root", "abcd*1234");
    }

    @Override
    public void crearUnidadDactica(UnidadDidactica uniDidac) {
        
                try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO unidadDidactica (id, acronimo, titulo, evaluacion, descripcion) VALUES (?, ?, ?, ?, ?)")) {
        // Set the parameters for the PreparedStatement using the UnidadDidactica object
        ps.setInt(1, uniDidac.getId());
        ps.setString(2, uniDidac.getAcronimo());  // Assuming getAcronimo() returns a String
        ps.setString(3, uniDidac.getTitulo());    // Assuming getTitulo() returns a String
        ps.setString(4, uniDidac.getEvaluacion()); // Assuming getEvaluacion() returns a String
        ps.setString(5, uniDidac.getDescripcion()); // Assuming getDescripcion() returns a String
        
        // Execute the insert command (executeUpdate returns the number of affected rows)
        int rowsInserted = ps.executeUpdate();
        
        // Check if the insert was successful
        if (rowsInserted > 0) {
            System.out.println("A new UnidadDidactica was inserted successfully!");
        }
        
    } catch (SQLException e) {
        // Log the error in case of an SQL exception
        Logger.getLogger("controlador").severe(e.getLocalizedMessage());
    }
}


    @Override
    public void crearConvocatoria(ConvocatoriaExamen convoExam) {
        
                try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO convocatoriaexamen (id, convocatoria, descripcion, fecha, curso) VALUES (?, ?, ?, ?, ?)")) {
        // Set the parameters for the PreparedStatement using the UnidadDidactica object
        ps.setInt(1, convoExam.getId());
        ps.setString(2, convoExam.getConvocatoria());
        ps.setString(3, convoExam.getDescripcion());  // Assuming getAcronimo() returns a String
       ps.setDate(4, java.sql.Date.valueOf(convoExam.getFecha()));    // Assuming getTitulo() returns a String
        ps.setString(5, convoExam.getCurso()); // Assuming getEvaluacion() returns a String
        
        // Execute the insert command (executeUpdate returns the number of affected rows)
        int rowsInserted = ps.executeUpdate();
        
        // Check if the insert was successful
        if (rowsInserted > 0) {
            System.out.println("A new UnidadDidactica was inserted successfully!");
        }
        
    } catch (SQLException e) {
        // Log the error in case of an SQL exception
        Logger.getLogger("controlador").severe(e.getLocalizedMessage());
    }
    }

    @Override
    public Enunciado crearEnunciadoDeUnidadConvocatoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getEnunciadoUnidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getExamenConEnunciadoConcreto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getDocumentEnunciado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Enunciado asignarEnunciadoAConvocatoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
 
