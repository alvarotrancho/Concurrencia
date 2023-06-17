
//NO MODIFIQUE ESTA CLASE
class Cliente{
    private String nombre;
    private double precioCarro;
    private static java.util.Random r=new java.util.Random();
    private static int nn=0;
    private static String[] nombres={"Pepe","Juana", "Antonio","María", "Francisco","Marta","Julio","Ana"};
    public Cliente(){
        this.nombre=nombres[nn%nombres.length]+(nn/nombres.length);
        this.precioCarro=r.nextInt(10000)/100.0;
        nn++;
    }
    public String dameNombre(){
        return nombre;
    }

    public double damePrecioCarro(){
        return precioCarro;
    }
    public String toString(){
        return dameNombre()+": "+ damePrecioCarro()+"€";
    }
}
