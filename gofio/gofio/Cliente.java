package gofio;
public class Cliente extends Thread{
    
    private Tienda t;
    private int id, compras;
    
    public Cliente(int id, Tienda t){
        this.id=id;
        this.t=t;
        this.compras= 0;
    }
    
    public int comprado(){
        return compras;
    }
    
    public void run(){
        try{
            while(true) {
                int cantidad = ValoresSimulacion.cantidadAComprar();
                Log.intentandoComprar(this.id, t.stock(), cantidad, 1000);
                boolean adquirido = t.comprar(this.id,cantidad);
                if (adquirido == true) {
                    Log.comprado(this.id, t.stock(), cantidad);
                    compras += cantidad;
                    Thread.sleep(ValoresSimulacion.tiempoConsumoKilo() * cantidad);
                }else{
                    Log.noPudoComprar(this.id, t.stock(), cantidad);
                } 
                break;
            }    
        } catch (InterruptedException e){
            System.out.println("Hilo Interrumpido");
        }
    }

}
