package gofio;
public class Tienda{
    
    private int capacidad, stock;
    
    public Tienda(int capacidad){
        this.capacidad = capacidad;    
        this.stock = 0;    
    }
    
    public synchronized boolean vender(int idAgricultor, int sacos){ //El agricultor intenta vender N sacos de 20kg
        if (capacidad < (sacos * 20) + stock()){
            try{
                this.wait(ValoresSimulacion.esperaVenta());
                if (capacidad < (sacos * 20) + stock()) return false;
            } catch (InterruptedException e){
                System.out.println("Hilo interrumpido");
            }
        }
        stock += sacos * 20;
        notifyAll();
        return true;
    }
    
    public synchronized boolean comprar(int idCliente, int kilos){//El cliente intenta comprar N kilos
        if (kilos > stock()) {
            try{
                this.wait(ValoresSimulacion.esperaCompra());
                if (kilos > stock()) return false;
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido");
            }
        }
        stock -= kilos;
        notifyAll();
        return true;
    }
    
    public int stock(){ //kilos en stock
        return stock;
    }
    
}
