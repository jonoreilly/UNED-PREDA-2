import java.util.List;
import java.util.ArrayList;

public class SolucionadorDyVBasico {

    public static List<Paso> getSolucionDyVBasico(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas) throws Exception {
        
        int numeroDePostes = posteFinal - (posteInicial - 1);
        
        if (numeroDePostes < 3 && numeroDePiezas > 1) {
            
            throw new Exception("El problema no tiene solucion con menos de 3 postes");
            
        }
        
        long tiempoInicio = System.currentTimeMillis();
        
        List<Paso> pasos = moverPilaBasico(numeroDePiezas, posteInicial, posteFinal, posteInicial + 1);
        
        long tiempoFin = System.currentTimeMillis();
        
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        
        System.out.println("DyV basico (" + tiempoEjecucion/1000 + "s) : " + pasos.size() + " = " + pasos);
        
        System.out.println("Solucion valida: " + Juego.esSolucionValida(posteInicial, posteFinal, numeroDePiezas, pasos, posteInicial));
                
        return pasos;
        
    }
    
    // Movimientos igual a 2^piezas
    private static List<Paso> moverPilaBasico(Integer numeroDePiezas, Integer origen, Integer destino, Integer pivote) {
        
        List<Paso> pasos = new ArrayList<>();
    
        if (numeroDePiezas == 1) {
                        
            pasos.add(new Paso(origen, destino));
            
        } else {
            
            pasos.addAll(moverPilaBasico(numeroDePiezas - 1, origen, pivote, destino));
            
            pasos.add(new Paso(origen, destino));
            
            pasos.addAll(moverPilaBasico(numeroDePiezas - 1, pivote, destino, origen));
            
        }
        
        return pasos;
        
    }

}
