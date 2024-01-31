import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class Pdf {
    public static void generarPDF(ArrayList<Campeon> listaCampeones, String nombreArchivo) {
        Document document = new Document();

        try {
            // Crear una única instancia de PdfWriter y abrir el documento
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();

            // Agregar información de todos los campeones al documento PDF
            for (Campeon campeon : listaCampeones) {
                document.add(new Paragraph("ID: " + campeon.getId()));
                document.add(new Paragraph("Nombre: " + campeon.getName()));
                document.add(new Paragraph("Rol: " + campeon.getRole()));
                document.add(new Paragraph("Linea: " + campeon.getLane()));
                document.add(new Paragraph("Dificultad: " + campeon.getDifficulty()));
                document.add(new Paragraph("Tipo de ataque: " + campeon.getAttackType()));
                document.add(new Paragraph("Fecha lanzamiento: " + campeon.getReleaseYear()));
                document.add(new Paragraph("Lore: " + campeon.getLore()));
                document.add(new Paragraph("\n")); // Separador entre campeones
            }

            // Cerrar el documento después de agregar toda la información
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
