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

    public static boolean sonEstadosEquivalentes(Estado a, Estado b) {
        
        return a.getId().equals(b.getId());
        
    }
    
    public static String getIdEstado(Estado estado) {
        
        List<Stack<Integer>> postes = estado.getPostes();
        
        Stack<Integer> posteFinal = postes.removeLast();
        
        String id = "id-" 
            + String.join(",", posteFinal.stream().map(p -> p.toString()).toList()) + ";" 
            + String.join(";", postes.stream().sorted((a, b) 
                -> {
                    
                    Integer valorA = 0;
                    
                    Integer valorB = 0;
                    
                    if (a.size() > 0) {
                        
                        valorA = a.getLast();  
                        
                    }
                    
                    if (b.size() > 0) {
                        
                        valorB = b.getLast();  
                        
                    }
                    
                    return valorA.compareTo(valorB);
                    
                }
                ).map(poste 
                -> String.join(",", poste.stream().map(p -> p.toString()).toList())
                ).toList());
        
        return id;
        
    }

}
