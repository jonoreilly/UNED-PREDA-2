import java.util.List;
import java.util.ArrayList;

public class SolucionadorDyVBasico {

    public static List<Paso> getSolucion(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas) throws Exception {
        
        IO.traza("Buscando solucion con Divide y Venceras Basico para: { posteInicial: " + posteInicial + ", posteFinal: " + posteFinal + ", numeroDePiezas: " + numeroDePiezas);
    
        long tiempoInicio = System.currentTimeMillis();
        
        List<Paso> pasos = moverPila(numeroDePiezas, posteInicial, posteFinal, posteInicial + 1);
        
        long tiempoFin = System.currentTimeMillis();
        
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        
        if (IO.TRAZA) {
            
            IO.traza("Divide y Venceras basico (" + tiempoEjecucion/1000 + "s) : " + pasos.size() + " = " + pasos);
            
            IO.traza("Solucion valida: " + Juego.esSolucionValida(posteInicial, posteFinal, numeroDePiezas, pasos));
            
            Juego.reproducirPasos(posteInicial, posteFinal, numeroDePiezas, pasos);
                    
        }
    
        return pasos;
        
    }
    
    private static List<Paso> moverPila(Integer numeroDePiezas, Integer origen, Integer destino, Integer pivote) {
        
        List<Paso> pasos = new ArrayList<>();
    
        if (numeroDePiezas == 1) {
                        
            pasos.add(new Paso(origen, destino));
            
        } else {
            
            pasos.addAll(moverPila(numeroDePiezas - 1, origen, pivote, destino));
            
            pasos.add(new Paso(origen, destino));
            
            pasos.addAll(moverPila(numeroDePiezas - 1, pivote, destino, origen));
            
        }
        
        return pasos;
        
    }

}
