
public class Main{
    public static void main(String[] args){
         Contabilidad contabilidad = new Contabilidad();
         Cola cola = new Cola();
        // Cree aquí las 3 cajas e inicie los hilos
        Caja caja1= new Caja(cola, contabilidad);
        Caja caja2= new Caja(cola, contabilidad);
        Caja caja3= new Caja(cola, contabilidad);
        
        caja1.start();
        caja2.start();
        caja3.start();
        long tiempoActual = System.currentTimeMillis();
        java.util.Random r = new java.util.Random(13);
        for (int i = 0; i < 50; i++) {
            try {
                cola.añadir(new Cliente());
                System.out.println("Cliente "+i+"  añadir");
                int random=r.nextInt(400);
                Thread.sleep(random);
            } catch (InterruptedException exc) {}
        }
        cola.cerrar();
        try {
            caja1.join();
            caja2.join();
            caja3.join();
        } catch(InterruptedException e) {
        }
        // Espere a que terminen las cajas
        // Muestre la información de la contabilidad
        
        System.out.println(contabilidad.dameSaldo()+"");
        
        System.out.println(caja1.clientesTotales+" ,"+ caja1.facturado);
        System.out.println(caja2.clientesTotales+" ,"+ caja2.facturado);
        System.out.println(caja3.clientesTotales+" ,"+ caja3.facturado);
    }
}
