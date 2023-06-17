package bocateria;
import java.util.*;
class Empleado extends Thread {
    private String nombre;
    private MesaTrabajo mesa;
    private ColaPedidos cola;

    /* Se le pasan el nombre del empleado y la mesa de trabajo de donde coger 
    componentes para hacer bocatas, así como la cola de donde recibir pedidos. */
    public Empleado(String nombre, MesaTrabajo mesa, ColaPedidos cola) {
        this.nombre = nombre;
        this.mesa = mesa;
        this.cola = cola;
    }
    
    @Override
    public void run(){
        
    }
    
    
}
