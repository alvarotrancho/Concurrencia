package bicing;
import java.util.concurrent.ThreadLocalRandom;

public class Usuario extends Thread {
    
    private int id_user;
    private Estacion salida, llegada;
    
    public Usuario(int id_user, Estacion salida, Estacion llegada){
        this.id_user = id_user;
        this.salida = salida;
        this.llegada = llegada;
    }
    
    public void run(){
        try {
            Bicicleta bike_user = salida.alquila(id_user);
            if (bike_user != null){
                int time = ThreadLocalRandom.current().nextInt(4000,7000);
                Log.paseando(id_user, salida.getId(),time);
                Thread.sleep(time);
                llegada.devuelve(bike_user, id_user);
            }
            // Termina el proceso (muere el hilo)
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    
}