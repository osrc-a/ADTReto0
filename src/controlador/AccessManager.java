/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.ConvocatoriaExamen;
import clases.Enunciado;
import clases.UnidadDidactica;

/**
 *
 * @author oscar
 */
public interface AccessManager {
    
    public UnidadDidactica crearUnidadDactica();
    public ConvocatoriaExamen crearConvocatoria();
    public Enunciado crearEnunciadoDeUnidadConvocatoria();
    public void getEnunciadoUnidad();
    public void getExamenConEnunciadoConcreto();
    public void getDocumentEnunciado();
    public Enunciado asignarEnunciadoAConvocatoria();
}
