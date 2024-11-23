import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.Stack;

public class SolucionadorFuerzaBruta {
    
    public static List<Paso> getSolucionFuerzaBruta(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas) throws Exception {
                    
        long tiempoInicio = System.currentTimeMillis();
        
        Nodo solucion = SolucionadorFuerzaBruta.obtenerNodoSolucion(posteInicial, posteFinal, numeroDePiezas);
        
        long tiempoFin = System.currentTimeMillis();
        
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        
        if (solucion == null) {
            
            return null;
        
        }
        
        List<Paso> pasos = SolucionadorFuerzaBruta.getPasosRealizados(solucion);
        
        System.out.println("FuerzaBruta (" + tiempoEjecucion/1000 + "s) : " + pasos.size() + " = " + pasos);
        
        System.out.println("Solucion valida: " + Juego.esSolucionValida(posteInicial, posteFinal, numeroDePiezas, pasos, 0));
        
        return pasos;
        
    }
    
    public static Nodo obtenerNodoSolucion(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas) throws Exception {
        
        int numeroDePostes = posteFinal - (posteInicial - 1);
        
        Estado estadoInicial = FactoriaEstados.getEstadoInicial(numeroDePostes, numeroDePiezas);

        Estado estadoFinal = FactoriaEstados.getEstadoFinal(numeroDePostes, numeroDePiezas);
        
        Nodo nodoInicial = new Nodo(null, estadoInicial, null);
        
        List<String> idsEstadosExplorados = new ArrayList<>();
        
        List<Nodo> nodosParaExplorar = new ArrayList<>();
        
        idsEstadosExplorados.add(nodoInicial.getEstado().getId());

        nodosParaExplorar.add(nodoInicial);
        
        while (true) {
            
            if (nodosParaExplorar.size() == 0) {
                
                return null;
                
            }
            
            List<Nodo> nuevosNodosParaExplorar = new ArrayList<>();
            
            for (Nodo nodo : nodosParaExplorar) {
                
                if (FactoriaEstados.sonEstadosEquivalentes(nodo.getEstado(), estadoFinal)) {
                    
                    return nodo;
                    
                }
                
                for (Nodo nodoNuevo : obtenerMovimientosPosibles(nodo)) {
            
                    String idNodoNuevo = nodoNuevo.getEstado().getId();
                    
                    if (idsEstadosExplorados.contains(idNodoNuevo)) {
                        
                        continue;
                        
                    }
                    
                    nuevosNodosParaExplorar.add(nodoNuevo);
                    
                    idsEstadosExplorados.add(idNodoNuevo);
                    
                }
                
            }
                
            nodosParaExplorar = nuevosNodosParaExplorar;
            
        }
        
    }
    
    public static List<Nodo> obtenerMovimientosPosibles(Nodo padre) {
    
        List<Nodo> nuevasPosiblesSoluciones = new ArrayList<>();
        
        Estado estado = padre.getEstado();
        
        for (int origen = 0; origen < estado.getNumeroDePostes(); origen++) {
            
            if (estado.getPostes().get(origen).size() > 0) {
                
                for (int destino = 0; destino < estado.getNumeroDePostes(); destino++) {
                    
                    if (origen == destino) {
                        
                        continue;
                        
                    }
                    
                    List<Stack<Integer>> postes = estado.getPostes();
                    
                    // Evitar estados invalidos
                    if (!postes.get(destino).empty() 
                        && postes.get(origen).peek() > postes.get(destino).peek()) {
                        
                        continue;
                        
                    }
                    
                    Integer pieza = postes.get(origen).pop();
                    
                    postes.get(destino).add(pieza);
                    
                    Estado nuevoEstado = new Estado(postes);
                    
                    nuevasPosiblesSoluciones.add(new Nodo(new Paso(origen, destino), nuevoEstado, padre));
                
                }
                
            }
            
        }
        
        return nuevasPosiblesSoluciones;
        
    }
        
    public static List<Paso> getPasosRealizados(Nodo nodo) {
        
        List<Paso> pasos = new ArrayList<>();
        
        if (nodo.getPaso() == null || nodo.getPadre() == null) {
            
            return pasos;
        
        }
        
        pasos.addAll(SolucionadorFuerzaBruta.getPasosRealizados(nodo.getPadre()));
        
        pasos.add(nodo.getPaso());
        
        return pasos;
        
    }
    
}
