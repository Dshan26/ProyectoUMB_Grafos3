package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoggleMaps {
    public static double obtenerDistancia(String origen, String destino) throws IOException {
        String apiKey = "";//colocan su api creada desde google maps

        // Codificar los nombres de ubicación
        String origenCodificado = URLEncoder.encode(origen, "UTF-8");
        String destinoCodificado = URLEncoder.encode(destino, "UTF-8");

        // Formar la URL de la solicitud a la API de Google Maps
        String urlStr = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
                + origenCodificado + "&destinations=" + destinoCodificado + "&key=" + apiKey;

        // Realizar la peticion HTTP GET a la API de Google Maps
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Leer la respuesta de la API de Google Maps
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Analizar la respuesta JSON para obtener la distancia en kilómetros
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response.toString());
            String status = jsonObject.getString("status");
            if (!status.equals("OK")) {
                throw new RuntimeException("La solicitud a la API de Google Maps no tuvo éxito. Estado: " + status);
            }
            JSONArray rows = jsonObject.getJSONArray("rows");
            if (rows.length() == 0) {
                throw new RuntimeException("No se encontraron resultados en la respuesta de la API de Google Maps.");
            }
            JSONObject row = rows.getJSONObject(0);
            JSONArray elements = row.getJSONArray("elements");
            if (elements.length() == 0) {
                throw new RuntimeException("No se encontraron elementos en la respuesta de la API de Google Maps.");
            }
            JSONObject element = elements.getJSONObject(0);
            String elementStatus = element.getString("status");
            if (elementStatus.equals("NOT_FOUND")) {
                throw new RuntimeException("No se encontró una ruta válida entre los lugares especificados. Estado del elemento: " + elementStatus);
            } else if (!elementStatus.equals("OK")) {
                throw new RuntimeException("La API de Google Maps no pudo encontrar una ruta válida. Estado del elemento: " + elementStatus);
            }
            JSONObject distance = element.getJSONObject("distance");
            return distance.getDouble("value") / 1000.0;
        } catch (JSONException e) {
            throw new RuntimeException("Error al analizar la respuesta JSON de la API de Google Maps.", e);
        }
    }
}
