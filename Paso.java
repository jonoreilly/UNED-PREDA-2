public class Paso {
    
    private Integer origen;
    
    private Integer destino;
    
    public Paso(Integer origen, Integer destino) {
        
        this.origen = origen;
        
        this.destino = destino;
        
    }
    
    public Integer getOrigen() {
        
        return this.origen;
        
    }
    
    public Integer getDestino() {
        
        return this.destino;
        
    }
    
    public String toString() {
        
        return this.origen + "->" + this.destino;
        
    }
    
}
