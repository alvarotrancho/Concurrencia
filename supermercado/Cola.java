import java.util.LinkedList;
import java.util.Queue;
public class Cola {
    private volatile Queue queue = new LinkedList<Cliente>();
    boolean closed = false;
    private int colaMax=0;
    
    public void añadir(Cliente cliente){
        queue.add(cliente);
        System.out.println("Cliente "+cliente.dameNombre()+"  añadido a la cola");
        if(colaMax<queue.size()) colaMax=queue.size();
    }
    
    public synchronized Cliente sacar(){
        if(closed) return null;
        
        return (Cliente) queue.poll();
    }
    
    public void cerrar(){
        closed=true;
    }
    
    public int tamañoMáximo(){
        return colaMax;
    }
}
