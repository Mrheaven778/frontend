
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api {
    private static String apiUrl = "http://localhost:3000/api/champion";

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
