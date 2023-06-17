package dexito;
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) {
        int[] mesas = {2,3,4,2,4,5,4};
        Metre metre= new Metre(mesas);
        // Cree dos camareros y arranquelos
        Camarero c1, c2;
        c1 = new Camarero(metre);
        c2 = new Camarero(metre);
        
        c1.start();
        c2.start();
        
        for (int i= 0; i < 25; i++) {
            Tools.println(metre.estado());
            GrupoComensales grupo = new GrupoComensales(metre, 1 + i % 4);
            grupo.start();
            try{
                Thread.sleep(Tools.tiempoLlegada());
            }catch(InterruptedException e){}
        }
        metre.cerrarRestaurante();
        Tools.println("Esperando finalizar");
        // Espere aquí a que terminen los camareros
        try {
            c1.join();
            c2.join();
        } catch(InterruptedException ignored){}
        
        Tools.println(metre.estado());
    }
}

class Tools {
    private static long inicio = System.currentTimeMillis();
    private static Random r = new Random();
    static {
        r.setSeed(13);
    }
    public static long tiempoUsoMesa() {
        return 500 + r.nextInt(1000);
    }
    public static long tiempoLimpiezaMesa() {
        return 200 + r.nextInt(2000);
    }
    public static long tiempoLlegada() {
        return 1 + r.nextInt(1000);
    }
    public static void println(String info) {
        synchronized(System.out) {
            float lapse = (System.currentTimeMillis() - inicio)/1000f;
            System.out.println(String.format("%05.1f %s" , lapse , info));
        }
    }
}
