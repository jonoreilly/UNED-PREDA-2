import java.util.List;
import java.util.ArrayList;

public class Nodo {
    
    private Paso paso;
    
    private Estado estado;
    
    private Nodo padre;
    
    public Nodo(Paso paso, Estado estado, Nodo padre) {
        
        this.paso = paso;
        
        this.estado = estado;
        
        this.padre = padre;
        
    }
    
    public Paso getPaso() {
        
        return this.paso;
    
    }
    
    public Estado getEstado() {

        return this.estado;
    
    }

    public Nodo getPadre() {

        return this.padre;
    
    }
    
    public String toString() {
        
        return "{ paso: " + this.paso + ", estado: " + this.estado + ", padre: " + this.padre + " }";
        
    }
    
}