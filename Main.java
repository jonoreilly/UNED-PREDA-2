import java.util.ArrayList;
import java.util.List;

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
                
                int tamanioSolucionDyVSuperAvanzado = juego.getSolucionDyVSuperAvanzado().size();
                
                int tamanioSolucion =juego.getSolucion().size();
                
                if (tamanioSolucionDyVSuperAvanzado != tamanioSolucion) {
                    
                    System.out.println("#########################");
                    System.out.println("# SOLUCIONES DIFERENTES #");
                    System.out.println("#########################");
                    
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
