package gofio;
public class Agricultor extends Thread{
    
    private Tienda t;
    private int id, ventas;
    
    public Agricultor(int id, Tienda t){
        this.id=id;
        this.t = t;
    }
    
    public int vendido(){ 
        return ventas; 
    }
    
    public void run(){
        try{
            for (int i = 0; i < 5 ; i++){
                Thread.sleep(ValoresSimulacion.tiempoCosecha());
                int sacos = ValoresSimulacion.cantidadCosechada();
                Log.intentandoVender(this.id, t.stock(), sacos*20, 1000);
                boolean vendido = t.vender(this.id, sacos);
                if (vendido == true){
                    ventas += sacos * 20;
                    Log.vendido(this.id, t.stock(), sacos*20);
                } else {
                    Log.noPudoVender(this.id, t.stock(), sacos*20);
                }
            } 
        } catch (InterruptedException e){
            System.out.println("Hilo interrumpido");
        }
    }
}
