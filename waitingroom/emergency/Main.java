package emergency;
public class Main {
    private static WaitingRoom room;
    private static long ini=System.currentTimeMillis();
    public static void main(String[] args) {
        room = new WaitingRoom(6);
        Doctor[] doctors = new Doctor[3];
        String[] doctorNames = {"House", "Jeckill", "Watson"};
        for (int i=0; i<doctors.length; i++) {
            doctors[i] = new Doctor(doctorNames[i], room);
        }
        PatientGenerator pg = new PatientGenerator(room);
        //Descomente el siguiente bloque para que los doctores comiencen. */ 
        for (int i=0; i<doctors.length; i++) {
            doctors[i].start();
        }
        // Descomente el siguiente bloque para esperar a que lo doctores terminen. */
        try {
            for (int i=0; i<doctors.length; i++) {
                doctors[i].join();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        log("Main ends");
    }
    // Visualiza la situación actual, con mensaje.
    public static void log(String message) {
            long cur=System.currentTimeMillis();
            String t="";
            t += String.format("%6.2fs      \033[1;31m%s\033[m\n", (cur-ini)/1000.0, message);
            t += showWaitingRoom(room);
            t +="------------------------------------------------------------\n";
            System.out.print(t);
    }
    public static String showWaitingRoom(WaitingRoom wr) {
        String s = String.format("\033[1;%dm",33);
        Patient[] patients = wr.getAllPatients();
        for(int i=0; i< patients.length; i++) {
            s += patients[i] + " ";
        }
        s += "\033[m\n";
        return s;
    }
}
