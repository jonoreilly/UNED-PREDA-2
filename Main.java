import java.util.ArrayList;
import java.util.List;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class Main
{
    
    public static void main() throws Exception {
        
        int posteInicial = 1;
        
        int empiezaDesdePoste = 6;
        
        int empiezaDesdePiezas = 10;
        
        for (int posteFinal = 3; posteFinal <= 10; posteFinal++) {
            
            if (posteFinal < empiezaDesdePoste) {
                continue;
            }
                
            for (int piezas = 1; piezas <= 10; piezas++) {
                    
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
                    writer.append("\nposteFinal: " + posteFinal + ", piezas: " + piezas + "\n");
                    writer.append("DyVBasico " + (esSolucionDyVBasicoValida ? "" : "ERROR") + " (" + tiempoEjecucionDyVBasico/1000 + "s) [" + tamanioSolucionDyVBasico + "]: " + solucionDyVBasico + "\n");
                    writer.append("DyVAvanzado " + (esSolucionDyVAvanzadoValida ? "" : "ERROR") + " (" + tiempoEjecucionDyVAvanzado/1000 + "s) [" + tamanioSolucionDyVAvanzado + "]: " + solucionDyVAvanzado + "\n");
                    writer.append("DyVSuperAvanzado " + (esSolucionDyVSuperAvanzadoValida ? "" : "ERROR") + " (" + tiempoEjecucionDyVSuperAvanzado/1000 + "s) [" + tamanioSolucionDyVSuperAvanzado + "]: " + solucionDyVSuperAvanzado + "\n");
                    writer.append("FuerzaBruta " + (esSolucionFuerzaBrutaValida ? "" : "ERROR") + " (" + tiempoEjecucionFuerzaBruta/1000 + "s) [" + tamanioSolucionFuerzaBruta + "]: " + solucionFuerzaBruta + "\n");
                    
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
        
    }
    
}
