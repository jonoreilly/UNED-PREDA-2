import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;

public class Solucionador {
    
    public static <Nodo> Nodo getSolucion(
        Nodo nodoInicial, 
        Function<Nodo, Boolean> esSolucion,
        BiFunction<Nodo, Nodo, Boolean> sonIguales,
        Function<Nodo, List<Nodo>> explorarNodo) {
            
        List<Nodo> nodosExplorados = new ArrayList<>();
        
        List<Nodo> nodosParaExplorar = new ArrayList<>();
        
        nodosExplorados.add(nodoInicial);

        nodosParaExplorar.add(nodoInicial);
        
        int contadorIteraciones = 0;
        
        while (true) {
            
            /*
            System.out.println("Iteracion: " + contadorIteraciones 
                + "   nodosParaExplorar: " + nodosParaExplorar.size()
                + "   nodosExplorados: " + nodosExplorados.size());
                        
            contadorIteraciones++;
            */
            
            if (nodosParaExplorar.size() == 0) {
                
                return null;
                
            }
            
            List<Nodo> nuevosNodosParaExplorar = new ArrayList<>();
            
            for (Nodo nodo : nodosParaExplorar) {
                
                if (esSolucion.apply(nodo)) {
                    
                    return nodo;
                    
                }
                
                for (Nodo nodoNuevo : explorarNodo.apply(nodo)) {
            
                    boolean yaHaSidoVisitado = false;
                    
                    for (Nodo nodoExplorado : nodosExplorados) {
                        
                        if (sonIguales.apply(nodoExplorado, nodoNuevo)) {
                            
                            yaHaSidoVisitado = true;
                            
                            break;
                            
                        }
                        
                    }
                    
                    if (yaHaSidoVisitado) {
                        
                        continue;
                        
                    }
                    
                    nuevosNodosParaExplorar.add(nodoNuevo);
                    
                    nodosExplorados.add(nodoNuevo);
                    
                }
                
            }
                
            nodosParaExplorar = nuevosNodosParaExplorar;
            
        }
        
    }
    
}
