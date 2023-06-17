

public class Caja extends Thread{
    private Cola cola;
    private Contabilidad contabilidad;
     int clientesTotales=0;
     int facturado=0;
    
    public Caja(Cola cola, Contabilidad contabilidad){
        this.cola = cola;
        this.contabilidad=contabilidad;
    }
    
    @Override
    public void run (){
        try{
            while(true){
                Cliente client = cola.sacar();
                if(client !=null){
                    System.out.println("Cliente "+client.dameNombre()+"  atendiendo");
                    double tiempo = (client.damePrecioCarro()/35)*100;
                    Thread.sleep((long) tiempo);
                    contabilidad.añadeSaldo(client.damePrecioCarro());
                    clientesTotales++;
                    facturado+=client.damePrecioCarro();
                    System.out.println("Cliente "+client.dameNombre()+"  ha sido atendido");
                }else{
                    if(cola.closed) {
                        System.out.println("Caja cerrada");
                        break;
                    }
                }
            }
        }catch(InterruptedException ex){}
    }
}
