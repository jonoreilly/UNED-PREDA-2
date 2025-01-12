
public class DatosEntrada {
    
    private Integer posteInicial;
    
    private Integer posteFinal;
    
    private Integer numeroDePiezas;
    
    private Integer numeroDePostes;
    
    public DatosEntrada(Integer posteInicial, Integer posteFinal, Integer numeroDePiezas) {
        
        this.posteInicial = posteInicial;
        
        this.posteFinal = posteFinal;
        
        this.numeroDePiezas = numeroDePiezas;
        
        this.numeroDePostes = posteFinal - (posteInicial - 1);
        
    }
    
    public Integer getPosteInicial() {
        
        return this.posteInicial;
        
    }
    
    public Integer getPosteFinal() {
        
        return this.posteFinal;
        
    }
    
    public Integer getNumeroDePiezas() {
        
        return this.numeroDePiezas;
        
    }
    
    public Integer getNumeroDePostes() {
        
        return this.numeroDePostes;
        
    }
    
}
