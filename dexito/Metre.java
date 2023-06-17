package dexito;

public class Metre {
    enum Estado{
        Disponible, Ocupada, Sucia, Limpiandose
    }
    
    private volatile boolean restauranteAbierto = true;
    private final int numMesas;
    private int[] capacidadMesas;
    private Estado[] estadoMesas;
    private int[] ocupacionMesas;
    
    public Metre(int[] mesas){
        this.numMesas = mesas.length;
        capacidadMesas = new int[numMesas];
        estadoMesas = new Estado[numMesas];
        ocupacionMesas = new int[numMesas];
        for(int i = 0 ; i < numMesas; i++){
            capacidadMesas[i] = mesas[i];
            estadoMesas[i] = Estado.Disponible;
            ocupacionMesas[i] = 0;
        }
    }
    
    // Devuelve una mesa adecuada para el número de 
    // comensales, si no hay ninguna devuelve -1
    private int encuentraMesaLibre(int nComensales){
        for(int i = 0; i < numMesas; i++){
            if(estadoMesas[i] == Estado.Disponible 
                    && capacidadMesas[i] >= nComensales){
                return i;        
            }
        }
        
        return -1;
    }
    
    public synchronized int asignaMesa(int nComensales){
        int mesa = encuentraMesaLibre(nComensales);
        while(mesa == -1){
            try {
                wait();
            } catch (InterruptedException ignored){}
            mesa = encuentraMesaLibre(nComensales);
        }
        estadoMesas[mesa] = Estado.Ocupada;
        ocupacionMesas[mesa] = nComensales;
        
        return mesa;
    }
    
    public synchronized void desasignaMesa(int idMesa){
        estadoMesas[idMesa] = Estado.Sucia;
        ocupacionMesas[idMesa] = 0;
        
        notifyAll();
    }
    
    // Devuelve la primera mesa sucia que encuentre, si no hay ninguna
    // devuelve -1.
    private int encuentraMesaSucia(){
        for(int i = 0; i < numMesas; i++){
            if(estadoMesas[i] == Estado.Sucia){
                return i;
            }
        }
        
        return -1;
    }
    
    // Condición de parada de los camareros, si el restaurante cierra y quedan
    // mesas sucias/ocupadas esperan para limpiar esas mesas antes de acabar.
    // Si todas las mesas están disponible o limpiandose devuelve false, 
    // si alguna está ocupada o sucia devuelve true
    private boolean noTodaLimpias(){
        for(int i = 0; i < numMesas; i++){
            if(estadoMesas[i] != Estado.Disponible && 
               estadoMesas[i] != Estado.Limpiandose){
                return true;
            }
        }
        
        return false;
    }
    
    public synchronized int mesaALimpiar(){
        int mesa = encuentraMesaSucia();
        while( mesa == -1 && (restauranteAbierto || noTodaLimpias()) ){
            try {
                wait();
            } catch (InterruptedException ignored){}
            mesa = encuentraMesaSucia();
        }
        
        if(mesa != -1) {
            estadoMesas[mesa] = Estado.Limpiandose;
        }
        
        return mesa;
    }
    
    public synchronized void mesaLimpia(int idMesa){
        estadoMesas[idMesa] = Estado.Disponible;
        
        notifyAll();
    }
    
    public synchronized void cerrarRestaurante(){
        restauranteAbierto = false;
        
        notifyAll();
    }
    
    public String estado(){
        String estado = "";
        String disponible = "D ";
        String ocupada = "O ";
        String sucia = "S ";
        String limpiandose = "L ";
        for(int i = 0; i < numMesas; i++){
            estado += ocupacionMesas[i] + "/" + capacidadMesas[i];
            switch(estadoMesas[i]){
                case Disponible:
                    estado += disponible;
                    break;
                case Ocupada:
                    estado += ocupada;
                    break;
                case Sucia:
                    estado += sucia;
                    break;
                case Limpiandose:
                    estado += limpiandose;
                    break;
            }
        }
        
        return estado;
    }
}