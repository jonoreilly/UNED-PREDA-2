import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FactoriaEstados {

    public static Estado getEstadoInicial(
        Integer numeroDePostes, 
        Integer numeroDePiezas
        ) throws Exception {
        
        if (numeroDePostes < 0 || numeroDePiezas < 0) {
            throw new Exception();
        }
        
        List<Stack<Integer>> postes = new ArrayList<>();
        
        if (numeroDePostes > 0) {
            
            for (int i = 0; i < numeroDePostes; i++) {
                
                postes.add(new Stack<>());
                
            }
         
            for (int i = numeroDePiezas; i > 0; i--) {
                
                postes.get(0).add(i);
                
            }
            
        } 
        
        return new Estado(postes);
        
    }
    
    
    public static Estado getEstadoFinal(
        Integer numeroDePostes, 
        Integer numeroDePiezas
        ) throws Exception {
        
        if (numeroDePostes < 0 || numeroDePiezas < 0) {
            throw new Exception();
        }
        
        List<Stack<Integer>> postes = new ArrayList<>();
        
        if (numeroDePostes > 0) {
            
            for (int i = 0; i < numeroDePostes; i++) {
                
                postes.add(new Stack<>());
                
            }
         
            for (int i = numeroDePiezas; i > 0; i--) {
                
                postes.get(numeroDePostes - 1).add(i);
                
            }
            
        } 
        
        return new Estado(postes);
        
    }
    
    public static boolean sonEstadosIguales(Estado a, Estado b) {
        
        List<Stack<Integer>> postesA = a.getPostes();
        
        List<Stack<Integer>> postesB = b.getPostes();
        
        if (postesA.size() != postesB.size()) {
            
            return false;
            
        }
        
        for (int i = 0; i < postesA.size(); i++) {
            
            Stack<Integer> posteA = postesA.get(i);
            
            Stack<Integer> posteB = postesB.get(i);
            
            if (posteA.size() != posteB.size()) {
                
                return false;
                
            }
                
            for (int j = 0; j < posteA.size(); j++) {
                
                Integer piezaA = posteA.get(j);
                
                Integer piezaB = posteB.get(j);
                
                if (piezaA != piezaB) {
                    
                    return false;
                    
                }
            
            }
            
        }
        
        return true;
        
    }

}
