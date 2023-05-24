package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private final Map<String, Nodo> nodos;

    public Grafo() {
        nodos = new HashMap<>();
    }

    public void agregarNodo(String nombre) {
        nodos.put(nombre, new Nodo(nombre));
    }

    public void agregarArista(String origen, String destino, double distancia) {
        Nodo nodoOrigen = nodos.get(origen);
        Nodo nodoDestino = nodos.get(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            nodoOrigen.agregarVecino(nodoDestino, distancia);
            nodoDestino.agregarVecino(nodoOrigen, distancia);
        } else {
            throw new IllegalArgumentException("El nodo origen o destino no existe.");
        }
    }

    public List<String> getNodos() {
        return new ArrayList<>(nodos.keySet());
    }

    public List<String> getVecinos(String nombre) {
        Nodo nodo = nodos.get(nombre);
        if (nodo != null) {
            List<String> vecinos = new ArrayList<>();
            for (Nodo vecino : nodo.getVecinos()) {
                vecinos.add(vecino.getNombre());
            }
            return vecinos;
        } else {
            throw new IllegalArgumentException("El nodo no existe.");
        }
    }

    public double getDistancia(String origen, String destino) {
        Nodo nodoOrigen = nodos.get(origen);
        Nodo nodoDestino = nodos.get(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            double distancia = nodoOrigen.getDistanciaHacia(nodoDestino);
            if (distancia == Double.POSITIVE_INFINITY) {
                throw new IllegalArgumentException("No hay una conexión directa entre los nodos.");
            }
            return distancia;
        } else {
            throw new IllegalArgumentException("El nodo origen o destino no existe.");
        }
    }
    public boolean existeArista(String origen, String destino) {
        Nodo nodoOrigen = nodos.get(origen);
        Nodo nodoDestino = nodos.get(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            return nodoOrigen.esVecino(nodoDestino);
        } else {
            throw new IllegalArgumentException("El nodo origen o destino no existe.");
        }
    }

    public void imprimirMatrizAdyacencia() {
        List<String> nodos = getNodos();
        int n = nodos.size();

        double[][] matriz = new double[n][n];

        for (int i = 0; i < n; i++) {
            String origen = nodos.get(i);
            for (int j = 0; j < n; j++) {
                String destino = nodos.get(j);
                try {
                    double distancia = getDistancia(origen, destino);
                    matriz[i][j] = distancia;
                } catch (IllegalArgumentException e) {
                    matriz[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        // Imprimir la matriz de adyacencia
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

