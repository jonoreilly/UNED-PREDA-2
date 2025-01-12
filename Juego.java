import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.BiFunction;

public class Juego {
    
    public static void reproducirPasos(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas, List<Paso> pasos) throws Exception {
        
        int numeroDePostes = posteFinal - (posteInicial - 1);
        
        Estado estado = FactoriaEstados.getEstadoInicial(numeroDePostes, numeroDePiezas);
        
        IO.traza(estado.toString());
        
        for (Paso paso : pasos) {
            
            List<Stack<Integer>> postes = estado.getPostes();
            
            IO.traza(paso.toString());
            
            Integer pieza = postes.get(paso.getOrigen() - posteInicial).pop();
            
            postes.get(paso.getDestino() - posteInicial).add(pieza);
            
            estado = new Estado(postes);
            
            IO.traza(estado.toString());
            
        }
            
    }
    
    public static boolean esSolucionValida(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas, List<Paso> pasos) throws Exception {
        
        int numeroDePostes = posteFinal - (posteInicial - 1);
        
        Estado estado = FactoriaEstados.getEstadoInicial(numeroDePostes, numeroDePiezas);
        
        Estado estadoFinal = FactoriaEstados.getEstadoFinal(numeroDePostes, numeroDePiezas);
        
        for (Paso paso : pasos) {
            
            List<Stack<Integer>> postes = estado.getPostes();
            
            Integer pieza = postes.get(paso.getOrigen() - posteInicial).pop();
            
            postes.get(paso.getDestino() - posteInicial).add(pieza);
            
            estado = new Estado(postes);
            
            if (!Juego.esEstadoValido(estado)) {
                
                return false;
                
            }
            
        }
        
        return FactoriaEstados.sonEstadosEquivalentes(estado, estadoFinal);
        
    }
    
    public static boolean esEstadoValido(Estado estado) {
        
        for (Stack<Integer> poste : estado.getPostes()) {
            
            // Comprobar que cada pieza es mas grande que la anterior
            
            for (int i = 1; i < poste.size(); i++) {
                
                if (poste.get(i - 1) < poste.get(i)) {
                    
                    return false;
                    
                }
                
            }
            
        }
        
        return true;
        
    }
    
}
