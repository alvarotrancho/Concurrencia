package bocateria;
import java.util.*;
class MesaTrabajo {
    private int panes;
    private int lonchas;
    private int maxPanes;
    private MesaTrabajo mesa;
    
    public MesaTrabajo(int panes, int lonchas, int maxPanes, MesaTrabajo mesa){
        this.panes = panes;
        this.lonchas = lonchas;
        this.maxPanes = maxPanes;
        this.mesa = mesa;
    }
    
    /* Capacidad de panes en la mesa de trabajo */
    public MesaTrabajo(int maxPanes){
        this.maxPanes = 0;
    }

    /* Añade un pan cortado a la mesa de trabajo. */
    public synchronized void añadeUnPan() {
    }

    /* Añade un cierto número de lonchas a la mesa de trabajo. */
    public synchronized void añadeLonchas(int n) {
        mesa.añadeLonchas(n);
    }

    /* Toma un pan y 5 lonchas para hacer UN bocata. */
    public synchronized void tomarComponentesBocata() {
    }

    /* Devuelve el número de panes en la mesa. */
    public int panes() {
        return panes;
    }

    /* Devuelve el número de lonchas en la mesa. */
    public int lonchas() {
        return lonchas;
    }

}
