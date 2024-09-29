/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.ConvocatoriaExamen;
import clases.Enunciado;
import clases.UnidadDidactica;
import java.util.List;

/**
 *
 * @author oscar
 */
public interface AccessManager {
    
    public void  crearUnidadDactica(UnidadDidactica uniDidac);
    public void crearConvocatoria(ConvocatoriaExamen convoExam);
     public List<UnidadDidactica> getUnidad();
      public List<ConvocatoriaExamen> getConvocatoria();
    public void crearEnunciadoDeUnidadConvocatoria(Enunciado enunciado, int idUnidadDidactica, int idConvocatoria);
    public List<Enunciado> getEnunciadoUnidad(int idUnidadDidactica);
    public List<ConvocatoriaExamen> getExamenConEnunciadoConcreto(int idEnunciado);
    public String getRutaEnunciado(int idEnunciado);
    public void asignarEnunciadoAConvocatoria(int idConvocatoria, int idEnunciado);
    public List<Enunciado> getEnunciado();
}
