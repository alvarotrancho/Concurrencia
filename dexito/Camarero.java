package dexito;

public class Camarero extends Thread{
    private Metre metre;
    
    public Camarero(Metre metre){
        this.metre = metre;
    }
    
    public void run(){
        int mesa = metre.mesaALimpiar();
        while(mesa != -1){
            Tools.println(metre.estado());
            try {
                Thread.sleep(Tools.tiempoLimpiezaMesa());
            } catch (InterruptedException ignored) {}
            metre.mesaLimpia(mesa);
            Tools.println(metre.estado());
            mesa = metre.mesaALimpiar();
        }
    }
}