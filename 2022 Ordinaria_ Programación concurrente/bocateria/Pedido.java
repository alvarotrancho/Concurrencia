package bocateria;
/* NO MODIFIQUE ESTE FICHERO*/
public class Pedido {
    String nombre;
    int número;
    Pedido(String nomb, int núm) {
        nombre = nomb;
        número = núm;
    }

    /* Devuelve el nombre del paciente. */
    public String nombreCliente()  {
        return nombre;
    }
    
    /* Devuelve el nivel de urgencia del paciente. */
    public int númeroDeBocatas() {
        return número;
    }

    public String toString() {
        return nombre + "[" + número + "]";
    }
}
