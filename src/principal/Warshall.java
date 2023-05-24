package principal;

import java.util.*;
import java.util.PriorityQueue;

public class Warshall {
    public static List<String> warshall(Grafo grafo, String origen, String destino) {
        Map<String, Double> distancias = new HashMap<>();
        Map<String, String> antecesores = new HashMap<>();
        Set<String> nodosVisitados = new HashSet<>();
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();

        // Inicializar distancias con infinito y antecesores con null
        for (String nodo : grafo.getNodos()) {
            if (nodo.equals(origen)) {
                distancias.put(nodo, 0.0);
            } else {
                distancias.put(nodo, Double.POSITIVE_INFINITY);
            }
            antecesores.put(nodo, null);
        }

        // Agregar el nodo origen a la cola de prioridad
        colaPrioridad.offer(new Nodo(origen, 0.0));

        while (!colaPrioridad.isEmpty()) {
            Nodo nodoActual = colaPrioridad.poll();
            String nodo = nodoActual.getNombre();

            // Verificar si ya se ha visitado el nodo actual
            if (nodosVisitados.contains(nodo)) {
                continue;
            }

            nodosVisitados.add(nodo);

            // Obtener los vecinos del nodo actual
            List<String> vecinos = grafo.getVecinos(nodo);

            // Calcular la distancia acumulada desde el nodo origen al vecino
            // Verificar si la nueva distancia es menor que la distancia almacenada
            vecinos.forEach(vecino -> {
                double distancia = distancias.get(nodo) + grafo.getDistancia(nodo, vecino);
                if (distancia < distancias.get(vecino)) {
                    distancias.put(vecino, distancia);
                    antecesores.put(vecino, nodo);
                    colaPrioridad.offer(new Nodo(vecino, distancia));
                }
            });
        }

        // Construir la ruta mínima desde el origen hasta el destino
        List<String> rutaMinima = new ArrayList<>();
        String nodoActual = destino;

        while (nodoActual != null) {
            rutaMinima.add(0, nodoActual);
            nodoActual = antecesores.get(nodoActual);
        }

        return rutaMinima;
    }

    private static class Nodo implements Comparable<Nodo> {
        private final String nombre;
        private final double distancia;

        public Nodo(String nombre, double distancia) {
            this.nombre = nombre;
            this.distancia = distancia;
        }

        public String getNombre() {
            return nombre;
        }

        public double getDistancia() {
            return distancia;
        }

        @Override
        public int compareTo(Nodo otro) {
            return Double.compare(distancia, otro.distancia);
        }
    }

    public static boolean[][] warshall(Grafo grafo) {
        List<String> nodos = grafo.getNodos();
        int n = nodos.size();
        boolean[][] matrizCaminos = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || grafo.existeArista(nodos.get(i), nodos.get(j))) {
                    matrizCaminos[i][j] = true;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrizCaminos[i][j] = matrizCaminos[i][j] || (matrizCaminos[i][k] && matrizCaminos[k][j]);
                }
            }
        }

        return matrizCaminos;
    }

}
