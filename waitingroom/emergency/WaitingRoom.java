package emergency;
import java.util.*;
/* MODIFIQUE ESTE CLASE PARA CUMPLIR LOS REQUISITOS */
class WaitingRoom {
    /* Capacidad de la sala de espera */
    private int size;
    private boolean open;
    private LinkedList<Patient> queue_patients;
    
    public WaitingRoom(int size){
        this.size = size;
        open = true;
        //queue_patients = new PriorityQueue<Patient>(new ComparatorPatient());
        queue_patients = new LinkedList<Patient>();
    }

    /* Cerrar la sala de espera.
       Lo usa la clase PatientGenerator. */
    public synchronized void close() {
        open = false;
        notifyAll();
    }

    /* Llega un paciente, limitado por la capacidad.
       Lo usa la clase PatientGenerator. */
    public synchronized void receivePatient(Patient p) {
        if (open == false || queue_patients.size() >= this.size){
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido");
            }
        }
        queue_patients.add(p);
        Collections.sort(queue_patients, Comparator.comparing(Patient::getInjureLevel).reversed());
        notify();
    }

    /* Devuelve el primer paciente de la cola, para ser atendido. */
    public synchronized Patient callPatient() {
        if (queue_patients.isEmpty() && open == false) return null;
        Patient p = queue_patients.poll();
        Main.log("Atendiendo al paciente " + p.getName());
        notify();
        return p;
    }

    /* Devuelve un array con los pacientes en cola de espera, en su orden. */
    /* La cola no la tiene que mantener con un array. */
    public synchronized Patient[] getAllPatients() {
        Patient [] patients = new Patient[queue_patients.size()];
        int i = 0;
        for (Patient p : queue_patients){
            patients[i] = p;
            i++;
        }
        return patients;
    }
    
}

