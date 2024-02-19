import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Utilitis {

    public static void sacarDatosXml(ArrayList<Campeon> campeones, DefaultTableModel modelo) {
        DOMXML.sacarDatosXML(campeones);
        for (Campeon campeon : campeones) {
            modelo.addRow(new Object[]{campeon.getId(), campeon.getName(), campeon.getRole(),
                    campeon.getLane(), campeon.getAttackType(), campeon.getDifficulty(),
                    campeon.getReleaseYear(), campeon.getLore()});
        }
    }

    public static ArrayList<Campeon> sacarDatosApi(ArrayList<Campeon> campeones, DefaultTableModel model) {
        String response = Api.peticionApi();
        if (response != null) {
            System.out.println("La api no responde ");
        }
        System.out.println(response);
        Json.guardadDatos(response);
        Json.getChapeones(response, campeones);
        for (Campeon campeon : campeones) {
            model.addRow(new Object[]{campeon.getId(), campeon.getName(), campeon.getRole(),
                    campeon.getLane(), campeon.getAttackType(), campeon.getDifficulty(),
                    campeon.getReleaseYear(), campeon.getLore()});
        }
        return campeones;
    }

    public static void sacarDatosJson(ArrayList<Campeon> campeones, DefaultTableModel modelo) {
        Json.getChapeones(Json.conseguirDatosJSON(), campeones);
        for (Campeon campeon : campeones) {
            modelo.addRow(new Object[]{campeon.getId(), campeon.getName(), campeon.getRole(),
                    campeon.getLane(), campeon.getAttackType(), campeon.getDifficulty(),
                    campeon.getReleaseYear(), campeon.getLore()});
        }
    }

}
