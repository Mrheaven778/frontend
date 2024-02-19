import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;


public class Json {
    public static void guardadDatos(String datos) {
        try (FileWriter myWriter = new FileWriter("champions.json")) {
            myWriter.write(datos);
            System.out.println("Se ha guardado el archivo");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
            e.printStackTrace();
        }
    }

    public static void getChapeones(String json, ArrayList<Campeon> campeones) {
            campeones.clear();
            JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Campeon campeon = new Campeon(jsonObject.getString("id"), jsonObject.getString("name"), jsonObject.getString("role"), jsonObject.getString("lane"), jsonObject.getString("attackType"), jsonObject.getInt("difficulty"), jsonObject.getInt("releaseYear"), jsonObject.getString("lore"));
            campeones.add(campeon);
        }
    }

    public static String conseguirDatosJSON() {
        try {
            FileReader reader = new FileReader("datosCampeones.json");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            String data = "";
            while ((line = bufferedReader.readLine()) != null) {
                data += line;
            }
            reader.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    	
    }


}
