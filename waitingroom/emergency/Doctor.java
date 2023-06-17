package emergency;
/* MODIFIQUE ESTE CLASE PARA CUMPLIR LOS REQUISITOS */
class Doctor extends Thread{
    /* Se le pasan el nombrel del doctor y la sala de espera que atiende. */
    private String n;
    private WaitingRoom room;
    
    public Doctor(String n, WaitingRoom room) {
        this.n = n;
        this.room = room;
    }
    
    public void run(){
        try {
            Thread.sleep(2000);
            Patient p = room.callPatient();
            while (p != null){
                Thread.sleep(p.getInjureLevel()*200);
                p = room.callPatient();
            }   
        } catch(InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
    }
}
