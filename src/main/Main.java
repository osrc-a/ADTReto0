package main;

import clases.ConvocatoriaExamen;
import clases.UnidadDidactica;
import controlador.AccessManager;
import controlador.DataManagerFactory;
import java.time.LocalDate;
import recursos.Utilidades;

public class Main {

    public static void main(String[] args) {
        int opcion;
        UnidadDidactica unidad;
        ConvocatoriaExamen convo;

        do {
            // Mostrar el menú
            System.out.println("====== Menú ======");
            System.out.println("1. Crear unidad didactica");
            System.out.println("2. Opción 2");
            System.out.println("3. Opción 3");
            System.out.println("4. Opción 4");
            System.out.println("5. Opción 5");
            System.out.println("6. Opción 6");
            System.out.println("7. Salir");

            // Leer la opción del usuario
            opcion = Utilidades.leerInt("Seleccione una opción: ");

            // Estructura switch para el menú
            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la Opción 1");
                    // Código para la opción 1
                    crearUnidadDidacticaMain();
                    crearConvocatoria();
                    break;
                case 2:
                    System.out.println("Has seleccionado la Opción 2");
                    // Código para la opción 2
                    break;
                case 3:
                    System.out.println("Has seleccionado la Opción 3");
                    // Código para la opción 3
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println(); // Separador de líneas
        } while (opcion != 4); // El bucle se repite hasta que se selecciona la opción de salida (4)
    }

    private static void crearUnidadDidacticaMain() {
        System.out.println("== Crear Unidad Didáctica ==");
        Integer id = Utilidades.leerInt("ID: ");
        String acronimo = Utilidades.introducirCadena("Acrónimo: ");
        String titulo = Utilidades.introducirCadena("Título: ");
        String evaluacion = Utilidades.introducirCadena("Evaluación: ");
        String descripcion = Utilidades.introducirCadena("Descripción: ");

        UnidadDidactica unidad = new UnidadDidactica(id, acronimo, titulo, evaluacion, descripcion);
        DataManagerFactory.accederADatos().crearUnidadDactica(unidad);
             
    }

    private static void crearConvocatoria() {
        System.out.println("== Crear Unidad Didáctica ==");
        int id = Utilidades.leerInt("Id: ");
        String convocatoria = Utilidades.introducirCadena("Convocatoria: ");
        String descripcion = Utilidades.introducirCadena("Descripción: ");
        LocalDate fecha = Utilidades.pidoFechaDMA("Fecha (dd-mm-aaaa) :");
        String curso = Utilidades.introducirCadena("Curso: ");        
        ConvocatoriaExamen convocatoriaExam = new ConvocatoriaExamen(id, convocatoria, descripcion, fecha, curso);
        DataManagerFactory.accederADatos().crearConvocatoria(convocatoriaExam);
    }
}