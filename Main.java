import java.util.List;
import java.util.Arrays;

public class Main
{
    
    public static void main(String[] args) throws Exception {
        
        List<String> argumentos = Arrays.asList(args);
        
        List<String> opciones = Arrays.asList("-h", "-t");
        
        List<String> ficheros = argumentos.stream().filter(s -> !opciones.contains(s)).toList();
        
        if (argumentos.contains("-h")) {
            
            IO.mostrarAyuda();
            
            return;
            
        }
        
        IO.TRAZA = argumentos.contains("-t");
        
        DatosEntrada datosEntrada;
        
        if (ficheros.size() > 0) {
            
            String ficheroEntrada = ficheros.get(0);
            
            datosEntrada = IO.leerArchivoEntrada(ficheroEntrada);
            
        } else {
            
            datosEntrada = IO.leerDatosPorConsola();
            
        }
        
        List<Paso> solucion = SolucionadorDyVSuperAvanzado.getSolucion(
            datosEntrada.getPosteInicial(), 
            datosEntrada.getPosteFinal(), 
            datosEntrada.getNumeroDePiezas()
            );
        
        if (ficheros.size() > 1) {
            
            IO.escribirResultado(ficheros.get(1), solucion);
            
        } else {
            
            IO.mostrarResultadoPorPantalla(solucion);
            
        }
               
    }
    
}
