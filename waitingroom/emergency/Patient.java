package emergency;
/* NO MODIFIQUE ESTE FICHERO, YA ESTA DESARROLLADO */
public class Patient {
    String name;
    int level;
    Patient(String n, int l) {
        name = n;
        level = l;
    }
    /* Devuelve el nombre del paciente */
    public String getName(){
        return name;
    }
    /* devuelve el nivel de urgencia del paciente*/
    public int getInjureLevel(){
        return level;
    }
    public String toString() {
        return name + "[" + level + "]";
    }
}
