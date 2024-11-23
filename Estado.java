import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

public class Estado {
    
    private List<Stack<Integer>> postes;
    
    private String id;
    
    public Estado(List<Stack<Integer>> postes) {
        
        this.postes = postes;
        
        this.id = FactoriaEstados.getIdEstado(this);
        
    }
    
    public List<Stack<Integer>> getPostes() {
        
        List<Stack<Integer>> clon = new ArrayList<>();
        
        for (Stack<Integer> poste : this.postes) {
            
            Stack<Integer> clonPoste = new Stack<>();
            
            clonPoste.addAll(poste);
            
            clon.add(clonPoste);
            
        }
        
        return clon;
        
    }
    
    public int getNumeroDePostes() {
        
        return this.postes.size();
        
    }

    public int getNumeroDePiezas() {
        
        int numeroDePiezas = 0;
        
        for (Stack<Integer> poste : this.postes) {
            
            numeroDePiezas += poste.size();
            
        }
        
        return numeroDePiezas;
        
    }
    
    public String getId() {
        
        return this.id;
        
    }
    
    public String toString() {
        
        return this.postes.toString();
        
    }
    
}
