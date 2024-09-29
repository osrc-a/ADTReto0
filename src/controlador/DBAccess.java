/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.ConvocatoriaExamen;
import clases.Dificultad;
import clases.Enunciado;
import clases.UnidadDidactica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class DBAccess implements AccessManager {

    private Connection conectar() throws SQLException {
        // Configura la conexión a la base de datos
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_adt", "root", "abcd*1234");
    }

    @Override
    public void crearUnidadDactica(UnidadDidactica uniDidac) {

        try (Connection conn = conectar();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO unidadDidactica (id, acronimo, titulo, evaluacion, descripcion) VALUES (?, ?, ?, ?, ?)")) {

            ps.setInt(1, uniDidac.getId());
            ps.setString(2, uniDidac.getAcronimo());
            ps.setString(3, uniDidac.getTitulo());
            ps.setString(4, uniDidac.getEvaluacion());
            ps.setString(5, uniDidac.getDescripcion());

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new UnidadDidactica was inserted successfully!");
            }

        } catch (SQLException e) {

            Logger.getLogger("controlador").severe(e.getLocalizedMessage());
        }
    }

    @Override
    public void crearConvocatoria(ConvocatoriaExamen convoExam) {

        try (Connection conn = conectar();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO convocatoriaexamen (id, convocatoria, descripcion, fecha, curso) VALUES (?, ?, ?, ?, ?)")) {

            ps.setInt(1, convoExam.getId());
            ps.setString(2, convoExam.getConvocatoria());
            ps.setString(3, convoExam.getDescripcion());
            ps.setDate(4, java.sql.Date.valueOf(convoExam.getFecha()));
            ps.setString(5, convoExam.getCurso());

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new UnidadDidactica was inserted successfully!");
            }

        } catch (SQLException e) {

            Logger.getLogger("controlador").severe(e.getLocalizedMessage());
        }
    }

    public UnidadDidactica consultarIdUnidad(int idUnidad) {
        UnidadDidactica unidad = null;
        ResultSet rs;

        try (Connection conn = conectar();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM unidadDidactica WHERE id = ?")) {
            ps.setInt(1, idUnidad); // Establecer el idUnidad en el PreparedStatement
            rs = ps.executeQuery();

            if (rs.next()) {
                unidad = new UnidadDidactica(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

        return unidad;
    }

    public ConvocatoriaExamen consultarIdConvocatoria(int idConvocatoria) {
        ConvocatoriaExamen convocatoria = null;
        ResultSet rs;

        try (Connection conn = conectar();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM convocatoriaExamen WHERE id = ?")) {
            ps.setInt(1, idConvocatoria);
            rs = ps.executeQuery();

            if (rs.next()) {
                convocatoria = new ConvocatoriaExamen();
                convocatoria.setId(rs.getInt(1));
                convocatoria.setConvocatoria(rs.getString(2));
                convocatoria.setDescripcion(rs.getString(3));
                java.sql.Date fechaSQL = rs.getDate(4);
                convocatoria.setFecha(fechaSQL.toLocalDate());
                convocatoria.setCurso(rs.getString(5));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

        return convocatoria;
    }

    @Override
    public void crearEnunciadoDeUnidadConvocatoria(Enunciado enunciado, int idUnidadDidactica, int idConvocatoria) {
        try (Connection conn = conectar()) {

            String insertEnunciadoQuery = "INSERT INTO enunciado (id, descripcion, nivel, disponible, ruta) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement psEnunciado = conn.prepareStatement(insertEnunciadoQuery)) {

                psEnunciado.setInt(1, enunciado.getId());
                psEnunciado.setString(2, enunciado.getDescripcion());
                psEnunciado.setString(3, enunciado.getNivel().name());
                psEnunciado.setBoolean(4, enunciado.isDisponible());
                psEnunciado.setString(5, enunciado.getRuta());

                int rowsInserted = psEnunciado.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Enunciado creado con éxito.");
                }

                UnidadDidactica unidad = consultarIdUnidad(idUnidadDidactica);
                if (unidad != null) {

                    String insertRelacionQuery = "INSERT INTO tiene (idUnidad, idEnunciado) VALUES (?, ?)";

                    try (PreparedStatement psRelacion = conn.prepareStatement(insertRelacionQuery)) {
                        psRelacion.setInt(1, unidad.getId());
                        psRelacion.setInt(2, enunciado.getId());

                        int rowsRelacion = psRelacion.executeUpdate();
                        if (rowsRelacion > 0) {
                            System.out.println("Relación entre enunciado y unidad creada con éxito.");
                        }
                    }
                } else {
                    System.out.println("No se encontró ninguna unidad didáctica con el ID proporcionado.");
                }

                ConvocatoriaExamen convocatoria = consultarIdConvocatoria(idConvocatoria);
                if (convocatoria != null) {

                    String updateConvocatoriaQuery = "UPDATE convocatoriaExamen SET idEnunciado = ? WHERE id = ?";

                    try (PreparedStatement psConvocatoria = conn.prepareStatement(updateConvocatoriaQuery)) {
                        psConvocatoria.setInt(1, enunciado.getId());
                        psConvocatoria.setInt(2, convocatoria.getId());

                        int rowsConvocatoria = psConvocatoria.executeUpdate();
                        if (rowsConvocatoria > 0) {
                            System.out.println("Convocatoria actualizada con éxito.");
                        }
                    }
                } else {
                    System.out.println("No se encontró ninguna convocatoria con el ID proporcionado.");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Enunciado> getEnunciadoUnidad(int idUnidadDidactica) {
        List<Enunciado> enunciados = new ArrayList<>();
        ResultSet rs;

        try (Connection conn = conectar();
                PreparedStatement ps = conn.prepareStatement("SELECT e.id, e.descripcion,"
                        + " e.nivel, e.disponible, e.ruta FROM enunciado e INNER JOIN tiene"
                        + " t ON e.id = t.idEnunciado WHERE t.idUnidad = ?")) {
            ps.setInt(1, idUnidadDidactica);
            rs = ps.executeQuery();

            while (rs.next()) {
                Enunciado enunciado = new Enunciado(
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        Dificultad.valueOf(rs.getString("nivel").toUpperCase()),
                        rs.getBoolean("disponible"),
                        rs.getString("ruta")
                );
                enunciados.add(enunciado);
            }

        } catch (SQLException e) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, e);
        }

        return enunciados;
    }

    @Override
    public List<ConvocatoriaExamen> getExamenConEnunciadoConcreto(int idEnunciado) {
        List<ConvocatoriaExamen> convocatorias = new ArrayList<>();
        ResultSet rs;

        try (Connection conn = conectar();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM convocatoriaExamen WHERE idEnunciado = ?")) {
            ps.setInt(1, idEnunciado);
            rs = ps.executeQuery();

            while (rs.next()) {
                ConvocatoriaExamen convocatoria = new ConvocatoriaExamen(
                        rs.getInt("id"),
                        rs.getString("convocatoria"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getString("curso")
                );
                convocatorias.add(convocatoria);
            }

        } catch (SQLException e) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, e);
        }

        return convocatorias;
    }

    @Override
    public String getRutaEnunciado(int idEnuncicado) {
        String ruta = null;
        ResultSet rs;

        try (Connection conn = conectar();
                PreparedStatement ps = conn.prepareStatement("select ruta from enunciado where id = ?")) {
            ps.setInt(1, idEnuncicado);
            rs = ps.executeQuery();

            if (rs.next()) {
                ruta = rs.getString("ruta");
            }

        } catch (SQLException e) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, e);
        }

        return ruta; // Puede devolver null si no se encuentra ninguna ruta
    }

    @Override
    public void asignarEnunciadoAConvocatoria(int idConvocatoria, int idEnunciado) {
        try (Connection conn = conectar();
                PreparedStatement ps = conn.prepareStatement("UPDATE convocatoriaExamen SET idEnunciado = ? WHERE id = ?")) {
            ps.setInt(1, idEnunciado);
            ps.setInt(2, idConvocatoria);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Enunciado asignado a la convocatoria con éxito.");
            }

        } catch (SQLException e) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public List<UnidadDidactica> getUnidad() {
        List<UnidadDidactica> unidadParaReferir = new ArrayList<>();
        ResultSet rs;
        try (Connection conn = conectar()) {
            PreparedStatement stmt = conn.prepareStatement("select * from unidadDidactica");
            rs = stmt.executeQuery();

            while (rs.next()) {
                UnidadDidactica unidad = new UnidadDidactica(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                unidadParaReferir.add(unidad);
            }

        } catch (SQLException e) {

        }

        return unidadParaReferir;

    }

    @Override
    public List<ConvocatoriaExamen> getConvocatoria() {
        List<ConvocatoriaExamen> convocatoriaParaReferir = new ArrayList<>();
        ResultSet rs;
        try (Connection conn = conectar()) {
            PreparedStatement stmt = conn.prepareStatement("select * from convocatoriaExamen");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ConvocatoriaExamen convocatoria = new ConvocatoriaExamen(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(4));
                convocatoriaParaReferir.add(convocatoria);
            }

        } catch (SQLException e) {

        }

        return convocatoriaParaReferir;

    }

    @Override
    public List<Enunciado> getEnunciado() {
        List<Enunciado> enunciados = new ArrayList<>();

        ResultSet rs;
        try (Connection conn = conectar()) {
            PreparedStatement stmt = conn.prepareStatement("select * from enunciado");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Enunciado enunciado = new Enunciado(rs.getInt(1), rs.getString(2), Dificultad.valueOf(rs.getString(3)), rs.getBoolean(4), rs.getString(5));
                enunciados.add(enunciado);
            }

        } catch (SQLException e) {

        }

        return enunciados;
    }
}
