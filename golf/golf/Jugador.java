package golf;
public class Jugador extends Thread{
    
    enum TipoJugador{experimentado, novato};
    private static java.util.Random rand= new java.util.Random();
    
    private int id;
    private TipoJugador tipo;
    private Club club;
    private int repeticiones;
    
    public Jugador(int id, TipoJugador tipo, Club club, int repeticiones){
        this.id = id;
        this.tipo = tipo;
        this.club = club;
        this.repeticiones = repeticiones;
    }
    
    public void run(){
        int pelotas, palos;
        String c;
        for(int i = 0; i < repeticiones; i++){
            if(tipo == TipoJugador.novato){
                pelotas = rand.nextInt(4) + 2;
                palos = 2;
                c = "n";
            } else {
                pelotas = 2;
                palos = rand.nextInt(4) + 2;
                c = "e";
            }
            Simulador.log(id + c + "["+ pelotas + "," + palos + "]" + " reserva" );
            club.reserva(palos, pelotas);
            Simulador.log(id + c + "["+ pelotas + "," + palos + "]" + " jugar" );
            try{
                Thread.sleep(Simulador.tiempoJuego());
            } catch(InterruptedException e){}
            club.devuelve(palos, pelotas);
            Simulador.log(id + c + "["+ pelotas + "," + palos + "]" + " devolver" );
            try{
                Thread.sleep(Simulador.tiempoDescanso());
            } catch(InterruptedException e){}
        }
    }
}
