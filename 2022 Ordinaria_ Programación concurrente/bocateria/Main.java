package bocateria;

public class Main {
    private static long ini=System.currentTimeMillis();
    private static MesaTrabajo mesa = new MesaTrabajo(10);
    private static ColaPedidos cola = new ColaPedidos();
    public static void main(String[] args) {
        Empleado[] empleados = new Empleado[3];
        String[] nombresEmpleados = {"Fermín", "Fran", "Willy"};
        for (int i=0; i<empleados.length; i++) {
            empleados[i] = new Empleado(nombresEmpleados[i], mesa, cola);
        }
        GeneradorPedidos gp = new GeneradorPedidos(cola);
        PincheCocina pc1 = new PincheCocina(mesa);
        PincheCocina pc2 = new PincheCocina(mesa);
        //Descomente el siguiente bloque para que los empleados comiencen. */ 
        for (int i=0; i<empleados.length; i++) {
            empleados[i].start();
        }
        // Descomente el siguiente bloque para esperar a que los empleados terminen. */
        try {
            gp.join();
            for (int i=0; i<empleados.length; i++) {
                empleados[i].join();
            }
            pc1.interrupt();
            pc2.interrupt();
        } catch(Exception e) {
            e.printStackTrace();
        }
        log("Termina main");
    }
    
    // Visualiza la situación actual, con mensaje explicativo.
    public static void log(String message) {
            long cur=System.currentTimeMillis();
            String t="";
            t += String.format("%6.1fs      \033[1;31m%s\033[m\n", (cur-ini)/1000.0, message);
            t += muestraMesaCola(mesa, cola);
            t +="·-----------------------------------------------------------\n";
            System.out.print(t);
    }
    
    public static String muestraMesaCola(MesaTrabajo mesa, ColaPedidos cola) {
        String s = String.format("\033[1;%dm",33);
        s += "panes=" + mesa.panes() + " lonchas=" + mesa.lonchas();
        s += " pedidos en cola=" + cola.númPedidos();
        s += "\033[m\n";
        return s;
    }
}
