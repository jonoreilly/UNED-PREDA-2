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
        
        //int posteFinal = 5;
        
        //int piezas = 7;
        
        //System.out.println("posteInicial: " + posteInicial + ", posteFinal: " + posteFinal + ", piezas: " + piezas);
        
        // Juego juego = new Juego(posteInicial, posteFinal, piezas);
        
        // juego.getSolucionDyVBasico();
        // juego.getSolucionDyVAvanzado();
        // juego.getSolucionDyVSuperAvanzado();
        // juego.getSolucion();
        
        
        for (int posteFinal = 3; posteFinal <= 8; posteFinal++) {
                
            for (int piezas = 1; piezas <= 8; piezas++) {
                
                System.out.println("posteInicial: " + posteInicial + ", posteFinal: " + posteFinal + ", piezas: " + piezas);
                
                Juego juego = new Juego(posteInicial, posteFinal, piezas);
                
                // DyV Super Avanzado
                
                long tiempoInicioDyVSuperAvanzado = System.currentTimeMillis();
        
                List<Paso> solucionDyVSuperAvanzado = juego.getSolucionDyVSuperAvanzado();
                        
                long tiempoFinDyVSuperAvanzado = System.currentTimeMillis();
                
                long tiempoEjecucionDyVSuperAvanzado = tiempoFinDyVSuperAvanzado - tiempoInicioDyVSuperAvanzado;

                int tamanioSolucionDyVSuperAvanzado = solucionDyVSuperAvanzado.size();
                
                // Fuerza Bruta
                
                long tiempoInicioFuerzaBruta = System.currentTimeMillis();
                        
                List<Paso> solucionFuerzaBruta =juego.getSolucion();
                        
                long tiempoFinFuerzaBruta = System.currentTimeMillis();
                
                long tiempoEjecucionFuerzaBruta = tiempoFinFuerzaBruta - tiempoInicioFuerzaBruta;
                
                int tamanioSolucionFuerzaBruta =solucionFuerzaBruta.size();
                
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                              new FileOutputStream("filename.txt", true), "utf-8"))) {
                   writer.append("\nposteFinal: " + posteFinal + ", piezas: " + piezas + "\n");
                   writer.append("DyVSuperAvanzado (" + tiempoEjecucionDyVSuperAvanzado/1000 + "s) [" + tamanioSolucionDyVSuperAvanzado + "]: " + solucionDyVSuperAvanzado + "\n");
                   writer.append("FuerzaBruta (" + tiempoEjecucionFuerzaBruta/1000 + "s) [" + tamanioSolucionFuerzaBruta + "]: " + solucionFuerzaBruta + "\n");
                
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
       
        /*
        // posteInicial = 1
        // posteFinal = 5
        // piezas = 7
        List<Paso> pasos = new ArrayList<>();
        pasos.add(new Paso(0,1));
        pasos.add(new Paso(0,2));
        pasos.add(new Paso(0,3));
        pasos.add(new Paso(0,4));
        pasos.add(new Paso(1,2));
        pasos.add(new Paso(0,1));
        pasos.add(new Paso(3,1));
        pasos.add(new Paso(0,3));
        pasos.add(new Paso(4,3));
        pasos.add(new Paso(0,4));
        pasos.add(new Paso(3,0));
        pasos.add(new Paso(3,4));
        pasos.add(new Paso(1,3));
        pasos.add(new Paso(1,4));
        pasos.add(new Paso(0,4));
        pasos.add(new Paso(2,0));
        pasos.add(new Paso(3,4));
        pasos.add(new Paso(2,4));
        pasos.add(new Paso(0,4));
        juego.reproducirPasos(pasos, 0);
        */
        
        
    }
    
}
