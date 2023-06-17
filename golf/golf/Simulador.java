package golf;
public class Simulador {
    private static long tini=System.currentTimeMillis();
    private static java.util.Random rand= new java.util.Random();
    
    public static void main(String[] args) {
        Club club = new Club(20,20);
        Jugador[] jugadores = new Jugador[14];
        for(int i = 0; i < 7 ; i++){
            jugadores[i] = new Jugador(i, Jugador.TipoJugador.novato, club, 25);
        }
        
        for(int i = 7; i < 14 ; i++){
            jugadores[i] = new Jugador(i, Jugador.TipoJugador.experimentado, club, 25);
        }
        
        for(int i = 0; i < 14; i++){
            jugadores[i].start();
        }
        
        try {
            for(int i = 0; i < 14; i++){
                jugadores[i].join();
            }
        } catch(InterruptedException e){}
        
        // Complete aquí la simulación
        log("Termina Simulador.main");
    }
    
    // Muestra información en la pantalla
    public static void log(String message) {
            long cur=System.currentTimeMillis();
            String t= String.format("%6.1fs      \033[1;33m%s\033[m\n", (cur-tini)/1000.0, message);
            System.out.print(t);
    }
    
    // Devuelve tiempo de juego en milisegundos
    public static int tiempoJuego() {
        return rand.nextInt(1000);
    }
    
    // Devuelve tiempo de descanso en milisegundos
    public static int tiempoDescanso() {
        return rand.nextInt(1000);
    }
}
