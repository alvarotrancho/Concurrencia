
//Modifique y añada lo que crea necesarios
public class Cliente extends Thread{
    private int id;
    private int racionesPapas;
    private int hamburguesas;
    private Puesto puesto;
    public Cliente(int id, Puesto puesto, int racionesPapas, int hamburguesas){
        this.id = id;
        this.racionesPapas = racionesPapas;
        this.hamburguesas = hamburguesas;
        this.puesto = puesto;
    }
    
    @Override
    public void run() {
            puesto.obtenerComida(hamburguesas,racionesPapas,id);
    }
}
