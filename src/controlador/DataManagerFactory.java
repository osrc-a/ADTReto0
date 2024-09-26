/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ResourceBundle;

/**
 *
 * @author oscar
 */

//Clase que se encarga de leer el fichero de lectura y en base a lo que lea elige de donde sacar la informacion
public class DataManagerFactory {
    
    public static AccessManager accederADatos(){

        String tipoDao = ResourceBundle.getBundle("recursos.readData").getString("dao.implementacion");
        
         if ("archivo".equals(tipoDao)) {
            return new FileAccess();
        } else if ("base_datos".equals(tipoDao)) {
            return new DBAccess();
        } else {
            throw new IllegalArgumentException("Tipo de DAO no soportado: " + tipoDao);
        }
    }
        
    }
