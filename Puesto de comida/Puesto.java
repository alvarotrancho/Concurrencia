

//Modifique y añada lo que crea necesarios

public class Puesto {
    private int racionesPapas;
    private int hamburguesas;
    private int maxRacionesPapas;
    private int maxHamburguesas;
    private boolean abierto;
    public Puesto(int maxRacionesPapas, int maxHamburguesas){
        this.racionesPapas = 0;
        this.hamburguesas = 0;
        this.maxRacionesPapas = maxRacionesPapas;
        this.maxHamburguesas = maxHamburguesas;
    }
    public void abre() {
        Simulador.log("Puesto abierto");
        this.abierto = true;
    }
    public void cierra() {
        Simulador.log("Puesto cerrado");
        this.abierto = false;
    }
    public boolean estáAbierto() {
        return abierto;
    }
    
    public synchronized int verPuesto(){
        if(hamburguesas<=racionesPapas) return 0;
        else return 1;
    }
    
    public synchronized void añadirHamburguesas(){
        try{   
                if((hamburguesas+4)<=maxHamburguesas){
                    Simulador.tiempoCocinar();
                    hamburguesas+=4;
                    notifyAll();
                }else{
                    wait();
                    añadirHamburguesas();
                }
        }catch(InterruptedException ex){}
    }
    
    public synchronized void añadirPapas(){
        try{    
                if((racionesPapas+4)<=maxRacionesPapas){
                    Simulador.tiempoCocinar();
                    racionesPapas+=4;
                    notifyAll();
                }else{
                    wait();
                    añadirPapas();
                }
        }catch(InterruptedException ex){}
    }
    
    public synchronized void obtenerComida(int clienteH, int clienteP, int clienteId){
        try{
            if(estáAbierto()){
                Simulador.log("Cliente "+clienteId+ " ["+ clienteH + ","+clienteP+"] llega al puesto");
                if(((racionesPapas-clienteP)>=0)&&((hamburguesas-clienteH)>=0)){
                    racionesPapas= racionesPapas - clienteP;
                    hamburguesas= hamburguesas - clienteH;
                    Simulador.log("Cliente "+clienteId+ " ["+ clienteH + ","+clienteP+"] consigue la comida");
                    notifyAll();
                    Simulador.tiempoComerHamburguesa();
                    Simulador.tiempoComerPapas();
                    Simulador.log("Cliente "+clienteId+ " ["+ clienteH + ","+clienteP+"] termina de comer");
                }else{
                    Simulador.log("Cliente "+clienteId+ " ["+ clienteH + ","+clienteP+"] espera a por la comida");
                    wait();
                    obtenerComida(clienteH,clienteP,clienteId);
                }
            }
        }catch(InterruptedException ex){}
    }
}
