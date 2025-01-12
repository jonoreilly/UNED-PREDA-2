import java.util.List;
import java.util.ArrayList;

public class SolucionadorDyVSuperAvanzado {

    public static List<Paso> getSolucion(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas) throws Exception {
                
        IO.traza("Buscando solucion con Divide y Venceras Super Avanzado para: { posteInicial: " + posteInicial + ", posteFinal: " + posteFinal + ", numeroDePiezas: " + numeroDePiezas);
    
        List<Integer> postes = new ArrayList<>();
        
        for (int poste = posteInicial; poste <= posteFinal; poste++) {
            
            postes.add(poste);
            
        }
        
        long tiempoInicio = System.currentTimeMillis();
        
        List<Paso> pasos = moverPila(numeroDePiezas, posteInicial, posteFinal, postes);
        
        long tiempoFin = System.currentTimeMillis();
        
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        
        if (IO.TRAZA) {
                
            IO.traza("Divide y Venceras Super Avanzado (" + tiempoEjecucion/1000 + "s) : " + pasos.size() + " = " + pasos);
                            
            IO.traza("Solucion valida: " + Juego.esSolucionValida(posteInicial, posteFinal, numeroDePiezas, pasos));
            
            Juego.reproducirPasos(posteInicial, posteFinal, numeroDePiezas, pasos);
            
        }
        
        return pasos;
        
    }
        
    private static List<Paso> moverPila(Integer numeroDePiezas, Integer origen, Integer destino, List<Integer> postes) {
        
        List<Paso> pasos = new ArrayList<>();
    
        List<Integer> postesSinOrigen = postes.stream().filter(p -> p != origen).toList();
    
        List<Integer> postesSinOrigenNiDestino = postes.stream().filter(p -> p != origen && p != destino).toList();
 
        if (numeroDePiezas == 1) {
                        
            pasos.add(new Paso(origen, destino));
            
        } else if(postesSinOrigenNiDestino.size() == 1) {
            
            Integer pivote = postesSinOrigenNiDestino.get(0);
        
            pasos.addAll(moverPila(numeroDePiezas - 1, origen, pivote, postes));
            
            pasos.add(new Paso(origen, destino));
            
            pasos.addAll(moverPila(numeroDePiezas - 1, pivote, destino, postes));
            
        } else {
    
            int piezasPorPoste = (numeroDePiezas - 1) / postesSinOrigenNiDestino.size();
    
            int piezasExtra = (numeroDePiezas - 1) % postesSinOrigenNiDestino.size();
            
            for (int i = 0; i < postesSinOrigenNiDestino.size(); i++) { 
                
                int piezasAMover = piezasPorPoste;
                
                if (i < piezasExtra) {
                
                    piezasAMover++;
                
                }
                
                if (piezasAMover == 0) {
                 
                    continue;
                    
                }
                
                Integer destinoParcial = postesSinOrigenNiDestino.get(i);
                
                List<Integer> postesParcial = new ArrayList<>(postesSinOrigenNiDestino.subList(i, postesSinOrigenNiDestino.size()));
                
                postesParcial.add(origen);
                
                postesParcial.add(destino);
                
                pasos.addAll(moverPila(piezasAMover, origen, destinoParcial, postesParcial));
                
            }
            
            pasos.add(new Paso(origen, destino));
            
            for (int i = postesSinOrigenNiDestino.size() - 1; i >= 0; i--) { 
                
                int piezasAMover = piezasPorPoste;
                
                if (i < piezasExtra) {
                
                    piezasAMover++;
                
                }
                
                if (piezasAMover == 0) {
                 
                    continue;
                    
                }
                
                Integer origenParcial = postesSinOrigenNiDestino.get(i);
                
                List<Integer> postesParcial = new ArrayList<>(postesSinOrigenNiDestino.subList(i, postesSinOrigenNiDestino.size()));
                
                postesParcial.add(origen);
                
                postesParcial.add(destino);
                
                pasos.addAll(moverPila(piezasAMover, origenParcial, destino, postesParcial));
                
            }
        
        }
        
        return pasos;
        
    }
        
}
