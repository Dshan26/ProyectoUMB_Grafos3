package principal;

public class Arista {
    private final String destino;
    private final double distancia;

    public Arista(String destino, double distancia) {
        this.destino = destino;
        this.distancia = distancia;
    }

    public String getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }
}
