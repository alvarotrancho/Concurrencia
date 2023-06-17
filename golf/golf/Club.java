package golf;
public class Club {
    private int totalPalos, totalPelotas;
    private volatile int palosDisponibles, pelotasDisponibles;
    
    public Club(int palos, int pelotas){
        this.totalPalos = palos;
        this.totalPelotas = pelotas;
        this.palosDisponibles = palos;
        this.pelotasDisponibles = pelotas;
    }
    
    public synchronized boolean reserva(int palos, int pelotas){
        while(palosDisponibles < palos || pelotasDisponibles < pelotas){
            try {
                wait();
            } catch (InterruptedException e){}
        }
        palosDisponibles -= palos;
        pelotasDisponibles -= pelotas;
        Simulador.log("Pelotas disponibles: " + pelotasDisponibles + ". Palos disponibles: " + palosDisponibles);
        notifyAll();
        return true;
    }
    
    public synchronized boolean devuelve(int palos, int pelotas){
        while(palosDisponibles + palos > totalPalos ||
                pelotasDisponibles + pelotas > totalPelotas){
            
            try {
                wait();
            }  catch(InterruptedException e){}       
        }
        palosDisponibles += palos;
        pelotasDisponibles += pelotas;
        Simulador.log("Pelotas disponibles: " + pelotasDisponibles + ". Palos disponibles: " + palosDisponibles);
        notifyAll();
        return true;
    }
}
