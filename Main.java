public class Main
{
    
    public static void main() throws Exception {
        
        int posteInicial = 1;
        
        int posteFinal = 5;
        
        int piezas = 7;
        
        System.out.println("posteInicial: " + posteInicial + ", posteFinal: " + posteFinal + ", piezas: " + piezas);
        
        Juego juego = new Juego(posteInicial, posteFinal, piezas);
        
        juego.getSolucionDyVBasico();
        juego.getSolucionDyVAvanzado();
        juego.getSolucion();
        
    }
    
}
