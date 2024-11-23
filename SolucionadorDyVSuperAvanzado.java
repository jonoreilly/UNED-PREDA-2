import java.util.List;
import java.util.ArrayList;

public class SolucionadorDyVSuperAvanzado {

    public static List<Paso> getSolucionDyVSuperAvanzado(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas) throws Exception {
        
        int numeroDePostes = posteFinal - (posteInicial - 1);
        
        if (numeroDePostes < 3 && numeroDePiezas > 1) {
            
            throw new Exception("El problema no tiene solucion con menos de 3 postes");
            
        }
        
        List<Integer> postes = new ArrayList<>();
        
        for (int poste = posteInicial; poste <= posteFinal; poste++) {
            
            postes.add(poste);
            
        }
        
        long tiempoInicio = System.currentTimeMillis();
        
        List<Paso> pasos = moverPilaSuperAvanzado(numeroDePiezas, posteInicial, posteFinal, postes);
        
        long tiempoFin = System.currentTimeMillis();
        
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        
        System.out.println("DyV SuperAvanzado (" + tiempoEjecucion/1000 + "s) : " + pasos.size() + " = " + pasos);
                
        System.out.println("Solucion valida: " + Juego.esSolucionValida(posteInicial, posteFinal, numeroDePiezas, pasos, posteInicial));
        
        // reproducirPasos(pasos);
        
        return pasos;
        
    }
        
    // Movimientos igual a (2^piezas)/(postes/3)
    private static List<Paso> moverPilaSuperAvanzado(Integer numeroDePiezas, Integer origen, Integer destino, List<Integer> postes) {
        
        List<Paso> pasos = new ArrayList<>();
    
        List<Integer> postesSinOrigen = postes.stream().filter(p -> p != origen).toList();
    
        List<Integer> postesSinOrigenNiDestino = postes.stream().filter(p -> p != origen && p != destino).toList();
 
        if (numeroDePiezas == 1) {
                        
            pasos.add(new Paso(origen, destino));
            
        } else if(postesSinOrigenNiDestino.size() == 1) {
            
            Integer pivote = postesSinOrigenNiDestino.get(0);
        
            pasos.addAll(moverPilaSuperAvanzado(numeroDePiezas - 1, origen, pivote, postes));
            
            pasos.add(new Paso(origen, destino));
            
            pasos.addAll(moverPilaSuperAvanzado(numeroDePiezas - 1, pivote, destino, postes));
            
        } else {
            
            //   x x x
            //   x x x x x
            //   x x x x x 
            // x x x x x x
            // | | | | | | |
    
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
                
                pasos.addAll(moverPilaSuperAvanzado(piezasAMover, origen, destinoParcial, postesParcial));
                
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
                
                pasos.addAll(moverPilaSuperAvanzado(piezasAMover, origenParcial, destino, postesParcial));
                
            }
        
        }
        
        return pasos;
        
    }
        
}
