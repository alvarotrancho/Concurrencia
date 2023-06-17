package dexito;

public class GrupoComensales extends Thread {
    private Metre metre;
    private int comensales;
    
    public GrupoComensales(Metre metre, int comensales){
        this.metre = metre;
        this.comensales = comensales;
    }
    
    public void run(){
        int mesa = metre.asignaMesa(comensales);
        Tools.println(metre.estado());
        try {
            Thread.sleep(Tools.tiempoUsoMesa());
        } catch (InterruptedException ignored){}
        metre.desasignaMesa(mesa);
        Tools.println(metre.estado());
    }
}