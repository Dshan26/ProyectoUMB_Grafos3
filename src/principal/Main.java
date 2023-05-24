package principal;

import java.io.IOException;
import java.util.List;

import static principal.GoggleMaps.obtenerDistancia;

public class Main {
    public static void main(String[] args) {
        // Crear el grafo con los nodos correspondientes a los Supercades y la universidad
        Grafo grafo = new Grafo();
        grafo.agregarNodo("Universidad Manuela Beltrán Sede Bogotá");
        grafo.agregarNodo("Supercade Américas");
        grafo.agregarNodo("Supercade Bosa");
        grafo.agregarNodo("Supercade Calle 13");
        grafo.agregarNodo("Supercade Suba");
        grafo.agregarNodo("Supercade Engativá");
        grafo.agregarNodo("Supercade Social");
        grafo.agregarNodo("Supercade Manitas");
        grafo.agregarNodo("Supercade CAD");
        grafo.agregarNodo("Rapicade Siete de Agosto");
        grafo.agregarNodo("RAPICADE CHAPINERO CALLE 53");

        try {
            // Calcular las distancias entre los nodos
            double distancia1 = obtenerDistancia("Universidad Manuela Beltrán Sede Bogotá", "Supercade Américas");
            double distancia2 = obtenerDistancia("Universidad Manuela Beltrán Sede Bogotá", "Supercade Bosa");
            double distancia3 = obtenerDistancia("Universidad Manuela Beltrán Sede Bogotá", "Supercade Calle 13");
            double distancia4 = obtenerDistancia("Universidad Manuela Beltrán Sede Bogotá", "Supercade Suba");
            double distancia5 = obtenerDistancia("Universidad Manuela Beltrán Sede Bogotá", "Supercade Engativá");
            double distancia6 = obtenerDistancia("Universidad Manuela Beltrán Sede Bogotá", "Supercade Social");
            double distancia7 = obtenerDistancia("Universidad Manuela Beltrán Sede Bogotá", "Supercade Manitas");
            double distancia8 = obtenerDistancia("Universidad Manuela Beltrán Sede Bogotá", "Supercade CAD");
            double distancia9 = obtenerDistancia("Universidad Manuela Beltrán Sede Bogotá", "Rapicade Siete de Agosto");
            double distancia10 = obtenerDistancia("Universidad Manuela Beltrán Sede Bogotá", "RAPICADE CHAPINERO CALLE 53");

            // Agregar las aristas al grafo con las distancias calculadas
            grafo.agregarArista("Universidad Manuela Beltrán Sede Bogotá", "Supercade Américas", distancia1);
            grafo.agregarArista("Universidad Manuela Beltrán Sede Bogotá", "Supercade Bosa", distancia2);
            grafo.agregarArista("Universidad Manuela Beltrán Sede Bogotá", "Supercade Calle 13", distancia3);
            grafo.agregarArista("Universidad Manuela Beltrán Sede Bogotá", "Supercade Suba", distancia4);
            grafo.agregarArista("Universidad Manuela Beltrán Sede Bogotá", "Supercade Engativá", distancia5);
            grafo.agregarArista("Universidad Manuela Beltrán Sede Bogotá", "Supercade Social", distancia6);
            grafo.agregarArista("Universidad Manuela Beltrán Sede Bogotá", "Supercade Manitas", distancia7);
            grafo.agregarArista("Universidad Manuela Beltrán Sede Bogotá", "Supercade CAD", distancia8);
            grafo.agregarArista("Universidad Manuela Beltrán Sede Bogotá", "Rapicade Siete de Agosto", distancia9);
            grafo.agregarArista("Universidad Manuela Beltrán Sede Bogotá", "RAPICADE CHAPINERO CALLE 53", distancia10);

            // Imprimir la matriz de adyacencia
            System.out.println("Matriz de adyacencia:");
            grafo.imprimirMatrizAdyacencia();

            // Imprimir las distancias del grafo
            System.out.println("Distancias del grafo:");
            for (String origen : grafo.getNodos()) {
                for (String destino : grafo.getVecinos(origen)) {
                    double distancia = grafo.getDistancia(origen, destino);
                    System.out.println("Distancia de " + origen + " a " + destino + ": " + distancia + " km");
                }
            }

            // Calcular la ruta mínima utilizando el algoritmo de Warshall
            String origen = "Universidad Manuela Beltrán Sede Bogotá";
            String destino = "Supercade Américas";
            List<String> rutaMinima = Warshall.warshall(grafo, origen, destino);

            System.out.println();
            // Imprimir la ruta mínima
            System.out.println("Ruta mínima de " + origen + " a " + destino + ":");
            for (String nodo : rutaMinima) {
                System.out.println(nodo);
            }

            // ¡Grafo creado exitosamente!
            System.out.println("Grafo creado exitosamente con las distancias calculadas.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
