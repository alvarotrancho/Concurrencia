package bocateria;
import java.util.*;
public class ColaPedidos {
    private volatile Queue colaPedidos = new LinkedList<Pedido>();
    boolean closed = false;
    private int numeroPedido;
    
    public ColaPedidos(){
       

    }
    
    /* Añade un pedido al final de la cola */
    public void añadePedido(Pedido p) {
        colaPedidos.add(p);
        System.out.println(" .nombreCliente()");
        
    }

    /* Devuelve el pedido en la cabeza de la cola o espera a que haya pedido.
    Devuelve null si la cola está cerrada y no hay pedidos. */
    public Pedido damePedido() {
        return null;
    }

    /* Devuelve el número de pedidos en cola */
    public int númPedidos() {
        return numeroPedido;
    }

    /* Cierra la cola de forma que damePedido devuelva null cuando ya no queden
    pedidos en cola. */
    public void cerrar() {
        closed = true;
    }
}