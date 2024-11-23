import java.util.List;
import java.util.ArrayList;

public class SolucionadorDyVAvanzado {

    public static List<Paso> getSolucionDyVAvanzado(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas) throws Exception {
        
        int numeroDePostes = posteFinal - (posteInicial - 1);
        
        if (numeroDePostes < 3 && numeroDePiezas > 1) {
            
            throw new Exception("El problema no tiene solucion con menos de 3 postes");
            
        }
        
        List<Integer> postes = new ArrayList<>();
        
        for (int poste = posteInicial; poste <= posteFinal; poste++) {
            
            postes.add(poste);
            
        }
        
        long tiempoInicio = System.currentTimeMillis();
        
        List<Paso> pasos = moverPilaAvanzado(numeroDePiezas, posteInicial, posteFinal, postes);
        
        long tiempoFin = System.currentTimeMillis();
        
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        
        System.out.println("DyV Avanzado (" + tiempoEjecucion/1000 + "s) : " + pasos.size() + " = " + pasos);
        
        System.out.println("Solucion valida: " + Juego.esSolucionValida(posteInicial, posteFinal, numeroDePiezas, pasos, posteInicial));
                
        return pasos;
        
    }
    
    // Movimientos igual a (2^piezas)/(postes/3)
    private static List<Paso> moverPilaAvanzado(Integer numeroDePiezas, Integer origen, Integer destino, List<Integer> postes) {
        
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
            
            pasos.addAll(moverPilaAvanzado(numeroDePiezas - 1, origen, pivote, postes));
            
            pasos.add(new Paso(origen, destino));
            
            pasos.addAll(moverPilaAvanzado(numeroDePiezas - 1, pivote, destino, postes));
            
        }
        
        return pasos;
        
    }

}
