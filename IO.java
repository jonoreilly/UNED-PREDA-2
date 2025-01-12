import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class IO {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static boolean TRAZA = false;
    
    public static void mostrarAyuda() {
        
        System.out.println("SINTAXIS: tareas [-t] [-h] [fichero entrada [fichero salida]]");
        System.out.println("    [-t]                    Traza el algoritmo");
        System.out.println("    [-h]                    Muestra esta ayuda");
        System.out.println("    [fichero entrada]       Nombre del fichero de entrada");
        System.out.println("    [fichero salida]        Nombre del fichero de salida");
        
    }
    
    public static DatosEntrada leerArchivoEntrada(String nombre) throws Exception {
        
        try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
            
            List<List<Integer>> lineas = br
                .lines()
                .map(l -> 
                    Arrays
                        .stream(l.split(" "))
                        .filter(s -> s.length() > 0)
                        .map(s -> Integer.parseInt(s))
                        .toList())
                .filter(l -> l.size() > 0)
                .toList();
            
            if (lineas.size() == 0) {
                
                throw new Exception("El archivo de entrada esta vacio");
                
            }
            
            if (lineas.size() != 1) {
                
                throw new Exception("El archivo de entrada tiene mas de una linea");
                
            }
            
            if (lineas.get(0).size() != 3) {
                
                throw new Exception(
                    "El archivo de entrada esta mal formateado:\n" +
                    "{posteInicial} {posteFinal} {numeroDePiezas}"
                    );
                
            }
            
            Integer posteInicial = lineas.get(0).get(0);

            Integer posteFinal= lineas.get(0).get(1);
            
            Integer numeroPiezas = lineas.get(0).get(2);
            
            if (posteFinal - posteInicial < 2) {
                
                throw new Exception("No existen soluciones para menos de 3 postes");
                
            }
            
            return new DatosEntrada(posteInicial, posteFinal, numeroPiezas);
            
        } catch(Exception ex) {
            
            System.out.println("Error leyendo archivo " + nombre + " : " + ex);
            
            throw ex;
        
        }
        
    }
    
    public static DatosEntrada leerDatosPorConsola() throws Exception {
        
        System.out.println();
        
        Integer posteInicial = leerNumeroPositivo("Poste inicial: ");
        
        Integer posteFinal; 
        
        while (true) {
            
            posteFinal = leerNumeroPositivo("Poste final: ");
            
            if (posteFinal - posteInicial >= 2) {
                
                break;
                
            }
                
            System.out.println("Valor invalido, no existen soluciones para menos de 3 postes");
              
        }
        
        Integer numeroPiezas; 
        
        while (true) {
            
            numeroPiezas = leerNumeroPositivo("Numero de piezas: ");
            
            if (numeroPiezas > 0) {
                
                break;
                
            }
                
            System.out.println("Valor invalido, debe haber al menos 1 pieza");
              
        }
        
        return new DatosEntrada(posteInicial, posteFinal, numeroPiezas);
        
    }
    
    private static String leerLinea(String mensaje) {
    
        System.out.println(mensaje);
        
        String entrada = scanner.nextLine().strip();
        
        return entrada;
        
    }
    
    private static Integer leerNumero(String mensaje) {
        
        while (true) {
            
            String entrada = leerLinea(mensaje);
            
            try {
                
                return Integer.parseInt(entrada);
                
            } catch (Exception ex) {
            
                System.out.println("Valor invalido, debe ser un numero");
                
            }
        
        }
        
    }
    
    private static Integer leerNumeroPositivo(String mensaje) {
        
        while (true) {
            
            Integer entrada = leerNumero(mensaje);
            
            if (entrada >= 0) {
                
                return entrada;
                
            }
            
            System.out.println("Valor invalido, debe ser un numero positivo");
        
        }
        
    }
    
    public static void escribirResultado(String nombre, List<Paso> pasos) throws Exception {
        
        try (BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nombre), "utf-8"))) {
            
            for(Paso paso : pasos) {
                
                wr.append(paso.getOrigen() + " " + paso.getDestino() + "\n");
                
            }
            
        } catch(Exception ex) {
            
            System.out.println("Error escribiendo a archivo " + nombre + " : " + ex);
            
            throw ex;
        
        }
        
    }
    
    public static void mostrarResultadoPorPantalla(List<Paso> pasos) {
    
        System.out.println();
        
        System.out.println("Solucion:");
    
        for(Paso paso : pasos) {
            
            System.out.println(paso.getOrigen() + " " + paso.getDestino() + "\n");
            
        }
        
    }
    
    public static void traza(String mensaje) {
        
        if (TRAZA) {
            
            System.out.println(mensaje);
            
        }
        
    }
    
}
