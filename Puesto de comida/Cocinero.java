
//Modifique y añada lo que crea necesarios
public class Cocinero extends Thread{
    private Puesto puesto;
    private int tareas;
    public Cocinero(Puesto puesto, int tareas) {
        this.puesto = puesto;
        this.tareas = tareas;
        puesto.abre();
    }
    
    
    @Override
    public void run() {
            while(true){
                if(tareas >0){
                    if(puesto.verPuesto()==0){
                        Simulador.log("Cocinero añade H");
                        puesto.añadirHamburguesas();
                        tareas--;
                    }else{
                        Simulador.log("Cocinero añade P");
                        puesto.añadirPapas();
                        tareas--;
                    }  
                }else{
                Simulador.log("Cocinero Cierra");
                puesto.cierra();
                break;
                }
            }
    }
}
