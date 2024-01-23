import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String response = Api.peticionApi();
        if (response != null) {
            System.out.println("La api no responde ");
        }
        System.out.println(response);
        Json.guardadDatos(response);
        ArrayList<Campeon> campeones =  Json.getChapeones(response);

        for (Campeon campeon : campeones) {
            System.out.println(campeon.getName());
            System.out.println(campeon.getId());
        }

    }

}

