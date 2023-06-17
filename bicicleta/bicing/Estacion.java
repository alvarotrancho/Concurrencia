package bicing;
import java.util.*;

public class Estacion {
    
    private Bicicleta bike;
    private int id, storage_capacity;
    private Queue<Bicicleta> list_bikes;
    
    public Estacion(int id, int storage_capacity){
        this.id = id;
        this.storage_capacity = storage_capacity;
        list_bikes = new LinkedList<Bicicleta>();
        for(int i = 0; i < storage_capacity ; i++){
            list_bikes.add(new Bicicleta((this.id * 1000) + i));
        }
        Log.creandoEstación(this.id,  this.storage_capacity);
    }
    
    public int getId(){
        return id;
    }
    
    public synchronized Bicicleta alquila(int id_user){
        if(list_bikes.isEmpty()){
            try {
                Log.esperandoAlquilar(id_user, this.id, 10000);
                this.wait(10000);
                if (list_bikes.isEmpty()){
                    Log.abandona(id_user, this.id);
                    return null;
                }
            } catch (InterruptedException e){
                System.out.println("Hilo interrumpido");
            }    
        }
        Bicicleta bike_user = list_bikes.poll();
        Log.alquila(id_user, this.id, bike_user.getId());
        notifyAll();
        return bike_user;
    }
    
    public synchronized void devuelve(Bicicleta bike, int id_user){
        if(list_bikes.size() == storage_capacity){
            try {
                Log.intentandoDevolver(id_user, this.id, bike.getId());
                this.wait();
            } catch (InterruptedException e){
                System.out.println("Hilo interrumpido");
            } 
        }
        Log.devuelve(id_user, this.id, bike.getId());
        list_bikes.add(bike);
        notifyAll();
    }
    
}

