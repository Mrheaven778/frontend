
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Api {
    private static String apiUrl = "https://proyecto-final-api-two.vercel.app/api/champion";

    public static void peticionApiPost(ArrayList<Campeon> campeones){
        // Datos que deseas enviar en la solicitud POST
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Campeon c : campeones){
            sb.append("{");
            sb.append("\"name\": \"" + c.getName() + "\",");
            sb.append("\"role\": \"" + c.getRole() + "\",");
            sb.append("\"lane\": \"" + c.getLane() + "\",");
            sb.append("\"attackType\": \"" + c.getAttackType() + "\",");
            sb.append("\"difficulty\": " + c.getDifficulty() + ",");
            sb.append("\"releaseYear\": " + c.getReleaseYear() + ",");
            sb.append("\"lore\": \"" + c.getLore() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        System.out.println(sb.toString());

        // URL de destino

        // Realizar la solicitud POST
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(sb.toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir la respuesta del servidor
            System.out.println("Código de respuesta: " + response.statusCode());
            System.out.println("Respuesta del servidor: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String peticionApi() {
        try {

            // Crear objeto URL
            URL url = new URL(apiUrl);

            // Abrir conexión
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar el método de solicitud
            connection.setRequestMethod("GET");

            // Obtener la respuesta del servidor
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Si la respuesta es OK (200), leer la respuesta del servidor
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                // Imprimir la respuesta
                System.out.println("Respuesta de la API: " + response.toString());
                return response.toString();
            } else {
                // Si la respuesta no es OK, imprimir el código de respuesta
                System.out.println("La solicitud no fue exitosa. Código de respuesta: " + responseCode);
            }

            // Cerrar la conexión
            connection.disconnect();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
