import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.BiFunction;

public class Juego
{
    
    private Integer posteInicial = 0;

    private Integer posteFinal = 0;
    
    private Integer numeroDePiezas = 0;
    
    private Integer numeroDePostes = 0;
    
    public Juego(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas) throws Exception {
        
        if (posteInicial < 0 || posteFinal < 0 || numeroDePiezas < 0) {
            throw new Exception();
        }
        
        this.posteInicial = posteInicial;
        
        this.posteFinal = posteFinal;
        
        this.numeroDePiezas = numeroDePiezas; 
        
    }
    
    public List<Paso> getSolucion() throws Exception {
        
        int numeroDePostes = this.posteFinal - (this.posteInicial - 1);
        
        Estado estadoInicial = FactoriaEstados.getEstadoInicial(numeroDePostes, this.numeroDePiezas);

        Estado estadoFinal = FactoriaEstados.getEstadoFinal(numeroDePostes, this.numeroDePiezas);
        
        PosibleSolucion nodoInicial = new PosibleSolucion(null, estadoInicial, null);
        
        Function<PosibleSolucion, Boolean> esSolucion = nodo -> FactoriaEstados.sonEstadosIguales(nodo.getEstado(), estadoFinal);
        
        BiFunction<PosibleSolucion, PosibleSolucion, Boolean> sonIguales = (nodoA, nodoB) -> FactoriaEstados.sonEstadosIguales(nodoA.getEstado(), nodoB.getEstado());
        
        Function<PosibleSolucion, List<PosibleSolucion>> explorarNodo = nodo -> this.obtenerMovimientosPosibles(nodo);
    
        long tiempoInicio = System.currentTimeMillis();
        
        PosibleSolucion solucion = Solucionador.getSolucion(nodoInicial, esSolucion, sonIguales, explorarNodo);
        
        long tiempoFin = System.currentTimeMillis();
        
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        
        if (solucion == null) {
            
            return null;
        
        }
        
        List<Paso> pasos = Juego.getPasos(solucion);
        
        System.out.println("Normal (" + tiempoEjecucion/1000 + "s) : " + pasos.size() + " = " + pasos);
        
        System.out.println("Solucion valida: " + esSolucionValida(pasos, 0));
        
        // reproducirPasos(pasos);
        
        return pasos;
    
    }
    
    public static List<PosibleSolucion> obtenerMovimientosPosibles(PosibleSolucion padre) {
    
        List<PosibleSolucion> nuevasPosiblesSoluciones = new ArrayList<>();
        
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
                    
                    nuevasPosiblesSoluciones.add(new PosibleSolucion(new Paso(origen, destino), nuevoEstado, padre));
                
                }
                
            }
            
        }
        
        return nuevasPosiblesSoluciones;
        
    }
    
    public static List<Paso> getPasos(PosibleSolucion posibleSolucion) {
        
        List<Paso> pasos = new ArrayList<>();
        
        if (posibleSolucion.getPaso() == null || posibleSolucion.getPadre() == null) {
            
            return pasos;
        
        }
        
        pasos.addAll(Juego.getPasos(posibleSolucion.getPadre()));
        
        pasos.add(posibleSolucion.getPaso());
        
        return pasos;
        
    }
    
    // Movimientos igual a 2^piezas
    private List<Paso> moverPilaBasico(Integer numeroDePiezas, Integer origen, Integer destino, Integer pivote) {
        
        List<Paso> pasos = new ArrayList<>();
    
        if (numeroDePiezas == 1) {
                        
            pasos.add(new Paso(origen, destino));
            
        } else {
            
            pasos.addAll(moverPilaBasico(numeroDePiezas - 1, origen, pivote, destino));
            
            pasos.add(new Paso(origen, destino));
            
            pasos.addAll(moverPilaBasico(numeroDePiezas - 1, pivote, destino, origen));
            
        }
        
        return pasos;
        
    }

    // Movimientos igual a (2^piezas)/(postes/3)
    private List<Paso> moverPilaAvanzado(Integer numeroDePiezas, Integer origen, Integer destino, List<Integer> postes) {
        
        List<Paso> pasos = new ArrayList<>();
    
        List<Integer> postesSinOrigenNiDestino = postes.stream().filter(p -> p != origen && p != destino).toList();
        
        if (numeroDePiezas <= (postesSinOrigenNiDestino.size() + 1)) {
            
            int piezasMovidas = 0;
            
            for (Integer poste : postesSinOrigenNiDestino) {
                
                if (piezasMovidas >= numeroDePiezas) {
                    
                    break;
                
                }
                
                pasos.add(new Paso(origen, poste));
                
                piezasMovidas++;
                
            }
                        
            pasos.add(new Paso(origen, destino));
            
            piezasMovidas = 0;
            
            for (Integer poste : postesSinOrigenNiDestino.reversed()) {
                
                if (piezasMovidas >= numeroDePiezas) {
                    
                    break;
                
                }
                
                pasos.add(new Paso(poste, destino));
                
                piezasMovidas++;
                
            }
            
        } else {
            
            Integer pivote = postesSinOrigenNiDestino.get(0);
            
            pasos.addAll(moverPilaAvanzado(numeroDePiezas - 1, origen, pivote, postes));
            
            pasos.add(new Paso(origen, destino));
            
            pasos.addAll(moverPilaAvanzado(numeroDePiezas - 1, pivote, destino, postes));
            
        }
        
        return pasos;
        
    }
    
    
    // Movimientos igual a (2^piezas)/(postes/3)
    private List<Paso> moverPilaSuperAvanzado(Integer numeroDePiezas, Integer origen, Integer destino, List<Integer> postes) {
        
        List<Paso> pasos = new ArrayList<>();
    
        List<Integer> postesSinOrigen = postes.stream().filter(p -> p != origen).toList();
    
        List<Integer> postesSinOrigenNiDestino = postes.stream().filter(p -> p != origen && p != destino).toList();
            
        if (postesSinOrigenNiDestino.size() == 1 || numeroDePiezas == 1) {
            
            Integer pivote = postesSinOrigenNiDestino.get(0);
            
            pasos.addAll(moverPilaBasico(numeroDePiezas, origen, destino, pivote));
            
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
    
    public List<Paso> getSolucionDyVBasico() throws Exception {
        
        int numeroDePostes = this.posteFinal - (this.posteInicial - 1);
        
        if (numeroDePostes < 3 && numeroDePiezas > 1) {
            
            throw new Exception("El problema no tiene solucion con menos de 3 postes");
            
        }
        
        long tiempoInicio = System.currentTimeMillis();
        
        List<Paso> pasos = moverPilaBasico(numeroDePiezas, posteInicial, posteFinal, posteInicial + 1);
        
        long tiempoFin = System.currentTimeMillis();
        
        long tiempoEjecucion = tiempoFin - tiempoInicio;
        
        System.out.println("DyV basico (" + tiempoEjecucion/1000 + "s) : " + pasos.size() + " = " + pasos);
        
        System.out.println("Solucion valida: " + esSolucionValida(pasos, posteInicial));
        
        // reproducirPasos(pasos);
        
        return pasos;
        
    }
    
    public List<Paso> getSolucionDyVAvanzado() throws Exception {
        
        int numeroDePostes = this.posteFinal - (this.posteInicial - 1);
        
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
        
        System.out.println("Solucion valida: " + esSolucionValida(pasos, posteInicial));
        
        // reproducirPasos(pasos);
        
        return pasos;
        
    }
    
    public List<Paso> getSolucionDyVSuperAvanzado() throws Exception {
        
        int numeroDePostes = this.posteFinal - (this.posteInicial - 1);
        
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
                
        System.out.println("Solucion valida: " + esSolucionValida(pasos, posteInicial));
        
        // reproducirPasos(pasos);
        
        return pasos;
        
    }
    
    public void reproducirPasos(List<Paso> pasos, int offset) throws Exception {
        
        int numeroDePostes = this.posteFinal - (this.posteInicial - 1);
        
        Estado estado = FactoriaEstados.getEstadoInicial(numeroDePostes, this.numeroDePiezas);
        
        System.out.println(estado);
        
        for (Paso paso : pasos) {
            
            List<Stack<Integer>> postes = estado.getPostes();
            
            System.out.println(paso);
            
            Integer pieza = postes.get(paso.getOrigen() - offset).pop();
            
            postes.get(paso.getDestino() - offset).add(pieza);
            
            estado = new Estado(postes);
            
            System.out.println(estado);
            
        }
        
    }
    
    public boolean esSolucionValida(List<Paso> pasos, Integer offset) throws Exception {
        
        int numeroDePostes = this.posteFinal - (this.posteInicial - 1);
        
        Estado estado = FactoriaEstados.getEstadoInicial(numeroDePostes, this.numeroDePiezas);
        
        Estado estadoFinal = FactoriaEstados.getEstadoFinal(numeroDePostes, this.numeroDePiezas);
        
        for (Paso paso : pasos) {
            
            List<Stack<Integer>> postes = estado.getPostes();
            
            Integer pieza = postes.get(paso.getOrigen() - offset).pop();
            
            postes.get(paso.getDestino() - offset).add(pieza);
            
            estado = new Estado(postes);
            
            if (!Juego.esEstadoValido(estado)) {
                
                return false;
                
            }
            
        }
        
        return FactoriaEstados.sonEstadosIguales(estado, estadoFinal);
        
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
