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
        
        /*
                 
        int posteInicial = 1;
        
        int empiezaDesdePoste = 3;
        
        int empiezaDesdePiezas = 1;
        
        for (int posteFinal = 3; posteFinal <= 20; posteFinal++) {
            
            if (posteFinal < empiezaDesdePoste) {
                continue;
            }
                
            for (int piezas = 1; piezas <= 20; piezas++) {
                    
                if (posteFinal == empiezaDesdePoste && piezas < empiezaDesdePiezas) {
                    continue;
                }
                
                System.out.println("posteInicial: " + posteInicial + ", posteFinal: " + posteFinal + ", piezas: " + piezas);
            
                // DyV Basico
                
                long tiempoInicioDyVBasico = System.currentTimeMillis();
        
                List<Paso> solucionDyVBasico = SolucionadorDyVBasico.getSolucionDyVBasico(posteInicial, posteFinal, piezas);
                
                boolean esSolucionDyVBasicoValida = Juego.esSolucionValida(posteInicial, posteFinal, piezas, solucionDyVBasico, posteInicial);
                
                long tiempoFinDyVBasico = System.currentTimeMillis();
                
                long tiempoEjecucionDyVBasico = tiempoFinDyVBasico - tiempoInicioDyVBasico;

                int tamanioSolucionDyVBasico = solucionDyVBasico.size();
                
                // DyV Avanzado
                
                long tiempoInicioDyVAvanzado = System.currentTimeMillis();
        
                List<Paso> solucionDyVAvanzado = SolucionadorDyVAvanzado.getSolucionDyVAvanzado(posteInicial, posteFinal, piezas);
                        
                boolean esSolucionDyVAvanzadoValida = Juego.esSolucionValida(posteInicial, posteFinal, piezas, solucionDyVAvanzado, posteInicial);
                
                long tiempoFinDyVAvanzado = System.currentTimeMillis();
                
                long tiempoEjecucionDyVAvanzado = tiempoFinDyVAvanzado - tiempoInicioDyVAvanzado;

                int tamanioSolucionDyVAvanzado = solucionDyVAvanzado.size();
                               
                // DyV Super Avanzado
                
                long tiempoInicioDyVSuperAvanzado = System.currentTimeMillis();
        
                List<Paso> solucionDyVSuperAvanzado = SolucionadorDyVSuperAvanzado.getSolucionDyVSuperAvanzado(posteInicial, posteFinal, piezas);
                        
                boolean esSolucionDyVSuperAvanzadoValida = Juego.esSolucionValida(posteInicial, posteFinal, piezas, solucionDyVSuperAvanzado, posteInicial);
                
                long tiempoFinDyVSuperAvanzado = System.currentTimeMillis();
                
                long tiempoEjecucionDyVSuperAvanzado = tiempoFinDyVSuperAvanzado - tiempoInicioDyVSuperAvanzado;

                int tamanioSolucionDyVSuperAvanzado = solucionDyVSuperAvanzado.size();
                
                // Fuerza Bruta
                
                long tiempoInicioFuerzaBruta = System.currentTimeMillis();
                        
                List<Paso> solucionFuerzaBruta = SolucionadorFuerzaBruta.getSolucionFuerzaBruta(posteInicial, posteFinal, piezas);
                        
                boolean esSolucionFuerzaBrutaValida = Juego.esSolucionValida(posteInicial, posteFinal, piezas, solucionFuerzaBruta, 0);
                
                long tiempoFinFuerzaBruta = System.currentTimeMillis();
                
                long tiempoEjecucionFuerzaBruta = tiempoFinFuerzaBruta - tiempoInicioFuerzaBruta;
                
                int tamanioSolucionFuerzaBruta =solucionFuerzaBruta.size();
                
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                              new FileOutputStream("soluciones.txt", true), "utf-8"))) {
                    //writer.append("\nposteFinal: " + posteFinal + ", piezas: " + piezas + "\n");
                    //writer.append("DyVBasico " + (esSolucionDyVBasicoValida ? "" : "ERROR") + " (" + tiempoEjecucionDyVBasico/1000 + "s) [" + tamanioSolucionDyVBasico + "]: " + solucionDyVBasico + "\n");
                    //writer.append("DyVAvanzado " + (esSolucionDyVAvanzadoValida ? "" : "ERROR") + " (" + tiempoEjecucionDyVAvanzado/1000 + "s) [" + tamanioSolucionDyVAvanzado + "]: " + solucionDyVAvanzado + "\n");
                    //writer.append("DyVSuperAvanzado " + (esSolucionDyVSuperAvanzadoValida ? "" : "ERROR") + " (" + tiempoEjecucionDyVSuperAvanzado/1000 + "s) [" + tamanioSolucionDyVSuperAvanzado + "]: " + solucionDyVSuperAvanzado + "\n");
                    //writer.append("FuerzaBruta " + (esSolucionFuerzaBrutaValida ? "" : "ERROR") + " (" + tiempoEjecucionFuerzaBruta/1000 + "s) [" + tamanioSolucionFuerzaBruta + "]: " + solucionFuerzaBruta + "\n");
                    
                    writer.append(posteFinal + ", " + piezas + ", " + tamanioSolucionDyVSuperAvanzado + "\n");
                    
                    if (tamanioSolucionDyVSuperAvanzado != tamanioSolucionFuerzaBruta) {
                        
                        writer.append("#########################\n");
                        writer.append("# SOLUCIONES DIFERENTES #\n");
                        writer.append("#########################\n");
                        
                        
                        System.out.println("#########################");
                        System.out.println("# SOLUCIONES DIFERENTES #");
                        System.out.println("#########################");
                        
                    }
                
                }
            
            }
        
        }
        
                */
               
    }
    
}
