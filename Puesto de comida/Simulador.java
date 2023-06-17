
public class Simulador {
    private static long tini=System.currentTimeMillis();
    private static java.util.Random rand= new java.util.Random();
    
    public static void main(String[] args) {
        Puesto puesto = new Puesto(10,10);
        Cliente[] clientes = new Cliente[20];
        Cocinero cocinero = new Cocinero(puesto,8);
        // Creamos los clientes
        for(int i = 0; i < clientes.length; i++) {
            int racionesPapas = rand.nextInt(4);
            int hamburguesas = 1 + rand.nextInt(3);
            clientes[i] = new Cliente(i+1, puesto, racionesPapas, hamburguesas);
        }
        // Descomente para ejecutar la simulación
        // Iniciamos el cocinero
        cocinero.start();
        try {
            Thread.sleep(100); // Damos tiemp para que el cocinero abra el puesto
        } catch(InterruptedException e) {
            log("Sleep interrumpido");
        }
        // Iniciamos los clientes
        for(int i = 0; i < clientes.length; i++) {
            clientes[i].start();
        }
        // Esperamos por el cocinero y los clientes
        try {
            cocinero.join();
            for(int i = 0; i < clientes.length; i++) {
                clientes[i].join();
            }
        } catch(InterruptedException e) {
            log("Join interrumpido");
        }

        //*/
        log("Termina Simulador");
    }
    
    // Muestra información en la pantalla
    public static void log(String message) {
            long cur=System.currentTimeMillis();
            String t= String.format("%6.1fs \033[1;33m%s\033[m\n", (cur-tini)/1000.0, message);
            System.out.print(t);
    }
    
    // Devuelve tiempo de comer hamburguesa
    public static int tiempoComerHamburguesa() {
        return 250 + rand.nextInt(250);
    }
    
    // Devuelve tiempo de comer ración papas
    public static int tiempoComerPapas() {
        return 100 + rand.nextInt(200);
    }
    // Devuelve tiempo necesario cocinar 4 Hamburguesas o 4 raciones de papas
    public static int tiempoCocinar() {
        return 500;
    }
}
