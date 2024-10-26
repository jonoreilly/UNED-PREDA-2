import java.util.List;
import java.util.ArrayList;

public class PosibleSolucion {
    
    private Paso paso;
    
    private Estado estado;
    
    private PosibleSolucion padre;
    
    public PosibleSolucion(Paso paso, Estado estado, PosibleSolucion padre) {
        
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

    public PosibleSolucion getPadre() {

        return this.padre;
    
    }
    
    public String toString() {
        
        return "{ paso: " + this.paso + ", estado: " + this.estado + ", padre: " + this.padre + " }";
        
    }
    
}