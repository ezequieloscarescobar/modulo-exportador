package exportador;

import estrategias.exportacion.FormaDeExportacion;
import fachada.FacadeExportador;

import java.util.*;

public class MainExample {
    private static Map<String, List<String>> datos = new HashMap<>();

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Bienvenido al Módulo de Exportación");
        System.out.println("Los datos se exportarán como tabla, ya sea en Excel o en PDF");
        System.out.println("A continuación, ingrese el nombre de las columnas (separadas por enters). Finalice su ingreso con un punto (.)");

        procesoDeIngresoDeColumnas(entrada);

        System.out.println("Ahora ingrese el delimitador que utilizará para separar los valores de una fila");
        String delimitador = entrada.nextLine();

        System.out.println("¡Perfecto! Ingrese las filas, separando los valores por columnas con el delimitador elegido.");
        System.out.println("Para ingresar una nueva fila, presione enter o punto (.) para finalizar");

        procesoDeIngresoDeFilas(entrada, delimitador);

        procesoDeExportacion(entrada);

        entrada.close();
    }

    private static void procesoDeIngresoDeColumnas(Scanner entrada){
        List<String> columnas = new ArrayList<>();
        Boolean hayMas = entrada.hasNext();

        while (hayMas){
            String nombreDeColumna = entrada.nextLine();
            if(!nombreDeColumna.equals(".")) {
                columnas.add(nombreDeColumna);
                hayMas = entrada.hasNext();
            }
            else hayMas = false;
        }
        agregarColumnas(columnas);
    }

    private static void procesoDeIngresoDeFilas(Scanner entrada, String delimitador){
        Integer nroDeFila = 1;
        Boolean hayMas = entrada.hasNext();

        while(hayMas){
            String unaFila = entrada.nextLine();
            if(!unaFila.equals(".")) {
                agregarFila(unaFila, nroDeFila, delimitador);
                nroDeFila++;
                hayMas = entrada.hasNext();
            }
            else hayMas = false;
        }
    }

    private static void procesoDeExportacion(Scanner entrada){
        System.out.println("¡Perfecto! ¿Cómo se llamará su archivo?");
        String nombreDelArchivo = entrada.nextLine();
        System.out.println("¡Excelente!");

        FormaDeExportacion formaDeExportacion = obtenerFormaDeExportacion(entrada);

        System.out.println("¡Todo listo! Se realizará la exportación ...");

        String rutaFinal = exportar(nombreDelArchivo, formaDeExportacion);

        System.out.println("¡Listo! Se han exportados los datos.");
        System.out.println("El archivo se encuentra en " + rutaFinal);
        System.out.println("¿Le gustaría exportarlo a otro formato? SI/NO");

        if(entrada.nextLine().equals("NO"))
            System.out.println("¡Ok!");
        else procesoDeExportacion(entrada);
    }

    private static FormaDeExportacion obtenerFormaDeExportacion(Scanner entrada){
        try{
            System.out.println("Elija el  formato de exportación que desee: EXCEL o PDF");
            String formatoDeExportacion = entrada.nextLine();
            return FormaDeExportacion.valueOf(formatoDeExportacion);
        }
        catch (Exception exception){
            System.out.println("¡Ops! Se ha ingresado un valor que no es válido");
            return obtenerFormaDeExportacion(entrada);
        }
    }

    private static String exportar(String nombreDelArchivo, FormaDeExportacion formaDeExportacion) {
        FacadeExportador facadeExportador = new FacadeExportador();
        return facadeExportador.exportar(datos, formaDeExportacion, nombreDelArchivo);
    }

    private static void agregarFila(String fila, Integer nroDefila, String delimitadorDeValores){
        String[] valores = fila.split(delimitadorDeValores);
        List<String> coleccionDeValores = new ArrayList<>();
        Collections.addAll(coleccionDeValores, valores);
        datos.put(nroDefila.toString(), coleccionDeValores);
    }

    private static void agregarColumnas(List<String> columnas){
        datos.put("0", columnas);
    }
}
