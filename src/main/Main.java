package main;

import clases.ConvocatoriaExamen;
import clases.Dificultad;
import clases.Enunciado;
import clases.UnidadDidactica;
import controlador.AccessManager;
import controlador.DataManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
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
            System.out.println("2. Crear enunciado agregando unidadades didacticas");
            System.out.println("3. Consultar enunciados por unidad didactica");
            System.out.println("4. Consultar convocatorias por enunciado");
            System.out.println("5. Visualizar el documento de texto del enunciado");
            System.out.println("6. Asignar enunciado a convocatoria");
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
                    crearEnunciado();
                    break;
                case 3:
                    System.out.println("Has seleccionado la Opción 3");
                    // Código para la opción 3
                    consultarEnunciadosPorUnidadDidactica();
                    break;
                case 4:
                    System.out.println("Has seleccionado la Opción 4");
                    // Codigo para la opción 4
                    consultarConvocatoriasPorEnunciado();
                    break;
                case 5:
                    System.out.println("Has seleccionado la Opción 5");
                    visualizarDocumentoTexto();
                    break;
                case 6:
                    System.out.println("Has seleccionado la Opción 6");
                    asignarEnunciadoConvocatoria();
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println(); // Separador de líneas
        } while (opcion != 7); // El bucle se repite hasta que se selecciona la opción de salida (4)
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

    private static void consultarEnunciadosPorUnidadDidactica() {
        System.out.println("== Consultar enunciados por unidad didactica ==");

        int idConsulta = Utilidades.leerInt("Introduzca el id de la unidad didactica de la que desea consultar los enunciados");
        boolean encontrado = false;

        List<Enunciado> enunciados = DataManagerFactory.accederADatos().getEnunciadoUnidad(idConsulta);

        for (int i = 0; i < enunciados.size(); i++) {
            System.out.println(enunciados.get(i).toString());
            encontrado = true;
        }

        if (!encontrado) {
            System.out.println("La unidad introducida no tiene ningun enunciado");
        }

    }

    private static void consultarConvocatoriasPorEnunciado() {
        System.out.println("== Consultar enunciados por unidad didactica ==");

        int idConsulta = Utilidades.leerInt("Introduzca el id del enunciado del que desea consultar las convocatorias en las que se ha utilizado");
        boolean encontrado = false;

        List<ConvocatoriaExamen> convocatorias = DataManagerFactory.accederADatos().getExamenConEnunciadoConcreto(idConsulta);

        for (int i = 0; i < convocatorias.size(); i++) {
            System.out.println(convocatorias.get(i).toString());
            encontrado = true;
        }

        if (!encontrado) {
            System.out.println("La unidad introducida no tiene ningun enunciado");
        }
    }

    private static void visualizarDocumentoTexto() {
        System.out.println("== Visualizar ruta ==");
        int id;
        String ruta;

        id = Utilidades.leerInt("Introduce el id del enunciado");
        ruta = DataManagerFactory.accederADatos().getRutaEnunciado(id);
        //leerDocumento(ruta);
        System.out.println(ruta);
    }

    private static void leerDocumento(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static void crearEnunciado() {
        List<UnidadDidactica> unidades;
        List<ConvocatoriaExamen> convocatorias;
        int idConvocatoriaSeleccionada;
        int idUnidadSeleccionada;
        System.out.println("Vamos a crear un enunciado");
        int id = Utilidades.leerInt("Introduce un id para el enunciado");
        String descripcion = Utilidades.introducirCadena("Escribe la descripcion");
        Dificultad nivel = null;
        do {
            try {
                nivel = Dificultad.valueOf(Utilidades.introducirCadena("Cual es la dificultad alta / media / baja").toUpperCase());
            } catch (IllegalArgumentException e) {
                // Si la entrada no es válida, mostrar mensaje y repetir el ciclo
                System.out.println("El nivel introducido no es válido. Inténtalo de nuevo.");
            }
        } while (nivel == null);
        boolean disponible = Utilidades.leerRespuesta("Esta visible para los examinados si/no");
        String ruta = Utilidades.introducirCadena("Indica donde se puede encontrar el enunciado");
        Enunciado enunciado = new Enunciado(id, descripcion, nivel, disponible, ruta);

        unidades = DataManagerFactory.accederADatos().getUnidad();
        convocatorias = DataManagerFactory.accederADatos().getConvocatoria();
        if (unidades.isEmpty() || convocatorias.isEmpty()) {
            System.out.println("No hay unidades o convocatorias");
        } else {
            for (UnidadDidactica unidad : unidades) {
                System.out.println("Id -> " + unidad.getId() + " Descripcion -> " + unidad.getDescripcion());

            }

            boolean encontrado = false;
            do {

                idUnidadSeleccionada = Utilidades.leerInt("Introduce el ID de la unidad a asignar al enunciado");

                // Buscar la unidad seleccionada
                for (UnidadDidactica unidad : unidades) {
                    if (unidad.getId() == idUnidadSeleccionada) {
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("Esa unidad no existe, inténtalo de nuevo.");
                }

            } while (!encontrado);
            encontrado = false;
            do {
                for (ConvocatoriaExamen convocatoria : convocatorias) {
                    System.out.println("Id -> " + convocatoria.getId() + " Descripcion -> " + convocatoria.getDescripcion());

                }

                idConvocatoriaSeleccionada = Utilidades.leerInt("Introduce el ID de la convocatoria a asignar al enunciado");

                // Buscar la convocatoria seleccionada
                for (ConvocatoriaExamen convocatoria : convocatorias) {
                    if (convocatoria.getId() == idConvocatoriaSeleccionada) {
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("Esa convocatoria no existe, inténtalo de nuevo.");
                }

            } while (!encontrado);

            DataManagerFactory.accederADatos().crearEnunciadoDeUnidadConvocatoria(enunciado, idUnidadSeleccionada, idConvocatoriaSeleccionada);
        }

    }

    private static void asignarEnunciadoConvocatoria() {
        boolean encontrado = false;
        List<Enunciado> enunciados;
        List<ConvocatoriaExamen> convocatorias;
        int idEnunciado;
        int idConvocatoria;
        enunciados = DataManagerFactory.accederADatos().getEnunciado();
        convocatorias = DataManagerFactory.accederADatos().getConvocatoria();

        if (enunciados.isEmpty() || convocatorias.isEmpty()) {
            System.out.println("No hay enunciados o convocatorias");
        } else {

            do {

                for (Enunciado enunciado : enunciados) {
                    System.out.println("Id -> " + enunciado.getId() + " Descripcion -> " + enunciado.getDescripcion());
                }

                idEnunciado = Utilidades.leerInt("Introduce el ID del enunciado");

                // Buscar el enunciado seleccionado
                for (Enunciado enunciado : enunciados) {
                    if (enunciado.getId() == idEnunciado) {
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("Esa unidad no existe, inténtalo de nuevo.");
                }
            } while (!encontrado);

            do {
                for (ConvocatoriaExamen convocatoria : convocatorias) {
                    System.out.println("Id -> " + convocatoria.getId() + " Descripcion -> " + convocatoria.getDescripcion());
                }

                idConvocatoria = Utilidades.leerInt("Introduce el ID de la convocatoria");

                // Buscar la convocatoria seleccionada
                for (ConvocatoriaExamen convocatoria : convocatorias) {
                    if (convocatoria.getId() == idConvocatoria) {
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("Esa unidad no existe, inténtalo de nuevo.");
                }
            } while (!encontrado);

            DataManagerFactory.accederADatos().asignarEnunciadoAConvocatoria(idConvocatoria, idEnunciado);
        }
    }
}
