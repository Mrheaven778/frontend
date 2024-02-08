import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
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
    public static void generPDFJasper(ArrayList<Campeon> listaCampeones) throws FileNotFoundException, JRException {
    	String outputFile = "campeones.pdf";
 
        /* Convert List to JRBeanCollectionDataSource */
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listaCampeones);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("COLLECT", itemsJRBean);
        
        //read jrxml file and creating jasperdesign object
        InputStream input = new FileInputStream(new File("Blank_A4.jrxml"));
        
                            
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        
        /*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        /*call jasper engine to display report in jasperviewer window*/
        JasperViewer.viewReport(jasperPrint);
        
        
        /* outputStream to create PDF */
        OutputStream outputStream = new FileOutputStream(new File(outputFile));
        
        
        /* Write content to PDF file */
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        System.out.println("File Generated");	

    	
    }
}
