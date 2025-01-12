import java.util.List;
import java.util.ArrayList;

public class SolucionadorDyVAvanzado {

    public static List<Paso> getSolucion(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas) throws Exception {
                
        IO.traza("Buscando solucion con Divide y Venceras Avanzado para: { posteInicial: " + posteInicial + ", posteFinal: " + posteFinal + ", numeroDePiezas: " + numeroDePiezas);
    
        List<Integer> postes = new ArrayList<>();
        
        for (int poste = posteInicial; poste <= posteFinal; poste++) {
            
            postes.add(poste);
            
        }
        
        long tiempoInicio = System.currentTimeMillis();
        
        List<Paso> pasos = moverPila(numeroDePiezas, posteInicial, posteFinal, postes);
        
        long tiempoFin = System.currentTimeMillis();
        
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        
        if (IO.TRAZA) {
            
            IO.traza("Divide y Venceras Avanzado (" + tiempoEjecucion/1000 + "s) : " + pasos.size() + " = " + pasos);
            
            IO.traza("Solucion valida: " + Juego.esSolucionValida(posteInicial, posteFinal, numeroDePiezas, pasos));
        
            Juego.reproducirPasos(posteInicial, posteFinal, numeroDePiezas, pasos);
            
        }
        
        return pasos;
        
    }
    
    private static List<Paso> moverPila(Integer numeroDePiezas, Integer origen, Integer destino, List<Integer> postes) {
        
        List<Paso> pasos = new ArrayList<>();
    
        List<Integer> postesSinOrigenNiDestino = postes.stream().filter(p -> p != origen && p != destino).toList();
        
        if (numeroDePiezas == 1) {
            
            pasos.add(new Paso(origen, destino));
                
        } else if ((numeroDePiezas - 1) <= postesSinOrigenNiDestino.size()) {
            
            List<Integer> postesAUsar = postesSinOrigenNiDestino.subList(0, numeroDePiezas - 1);
            
            for (Integer poste : postesAUsar) {
                
                pasos.add(new Paso(origen, poste));
                
            }
                        
            pasos.add(new Paso(origen, destino));
            
            for (Integer poste : postesAUsar.reversed()) {
                                
                pasos.add(new Paso(poste, destino));
                
            }
            
        } else {
            
            Integer pivote = postesSinOrigenNiDestino.get(0);
            
            pasos.addAll(moverPila(numeroDePiezas - 1, origen, pivote, postes));
            
            pasos.add(new Paso(origen, destino));
            
            pasos.addAll(moverPila(numeroDePiezas - 1, pivote, destino, postes));
            
        }
        
        return pasos;
        
    }

}
