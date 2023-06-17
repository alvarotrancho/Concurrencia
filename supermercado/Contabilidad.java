

// Parcialmente desarrollada
public class Contabilidad {
    private double total = 0;
    
    public synchronized void añadeSaldo(double saldo) {
        this.total += saldo;
    }
    public double dameSaldo() {
        return this.total;
    }
}
