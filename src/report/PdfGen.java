/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import ControlObjects.Reservation;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Vehicle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jingchuan Chen
 */
public class PdfGen {
    
    private static DateFormat df = new SimpleDateFormat("yyyy");

    private static Font TitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    
    
    public static void genDailyRentalReport(ArrayList<Vehicle> vlist) throws DocumentException, FileNotFoundException {
        String title = "Daily Rental Report " + getDate();

        genVehicleReport(vlist, title);

    }
    
    public static void genDailyReturnReport(ArrayList<Vehicle> vlist) throws DocumentException, FileNotFoundException {
        String title = "Daily Return Report " + getDate();

        genVehicleReport(vlist, title);
    }
    
    public static void genVehicleNotReturnReport(ArrayList<Reservation> notReturnList, String title) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(title + ".pdf"));
            document.open();
            
            document.newPage();
            addMetaData(document, title);
            addTitle(document, title);
            
            PdfPTable table = new PdfPTable(5);
            addTableContent(table, "Contract #", "Vehicle Class", "Return Date", "Customer Name", "Customer Phone");
            for (Reservation res : notReturnList) {
                addTableContent(table, Integer.toString(res.getContractNo()), res.getVehicleClass(), res.getReturnTime().toString(),
                        res.getCustomerName(), res.getCustomerPhone());
            }
            document.add(table);
            
            document.close();
        } catch (Exception ex) {
            Logger.getLogger(PdfGen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void genVehicleForSaleReport(ArrayList<Vehicle> vlist, String title) {
        try {
            
            
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(title + ".pdf"));
            document.open();
            
            document.newPage();
            addMetaData(document, title);
            addTitle(document, title);
            
            PdfPTable table = new PdfPTable(6);
            addTableContent(table, "Vehicle Number", "Vehicle Class", 
                    "Brand & Model", "Manufacturer Year", "Odometer", "Sale Price");
            for (Vehicle v : vlist) {
                addTableContent(table, Integer.toString(v.getVehicleNo()), v.getClassName(),
                        v.getMode(), df.format(v.getManufactureDate()),
                        Integer.toString(v.getOdometer()), v.getSellingPrice());
            }
            document.add(table);
            
            document.close();
        } catch (Exception ex) {
            Logger.getLogger(PdfGen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void genVehicleListReport(ArrayList<Vehicle> vlist, String title) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(title + ".pdf"));
            document.open();
            
            document.newPage();
            addMetaData(document, title);
            addTitle(document, title);
            
            PdfPTable table = new PdfPTable(6);
            addTableContent(table, "Vehicle No", "Vehicle Class", "Brand & Model", 
                    "Manufacturer Year", "Odometer", "Status");
            for (Vehicle v : vlist) {
                addTableContent(table, Integer.toString(v.getVehicleNo()), v.getClassName(),
                        v.getMode(), df.format(v.getManufactureDate()), Integer.toString(v.getOdometer()),
                        v.getStatus().toString());
            }
            document.add(table);
            
            document.close();
        } catch (Exception ex) {
            Logger.getLogger(PdfGen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void genVehicleReport(ArrayList<Vehicle> vlist, String title) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(title + ".pdf"));
            document.open();
            
            document.newPage();
            addMetaData(document, title);
            addTitle(document, title);
            
            PdfPTable table = new PdfPTable(5);
            addTableContent(table, "Plate Number", "Vehicle Class", "Brand & Model", "Manufacturer Year", "BranchID");
            for (Vehicle v : vlist) {
                addTableContent(table, v.getPlateNo(), v.getClassName(),
                        v.getMode(), v.getManufactureDate().toString(), Integer.toString(v.getBranchId()));
            }
            document.add(table);
            
            document.close();
        } catch (Exception ex) {
            Logger.getLogger(PdfGen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    private static String getDate() {
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        return df.format(new Date());

    }

    private static void addMetaData(Document document, String title) {
        document.addTitle("My first PDF");
        document.addAuthor("Super Rent");
        document.addCreator("Super Rent");
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void addTitle(Document document, String title) throws DocumentException {
        Paragraph para = new Paragraph();
        addEmptyLine(para, 1);
        para.add(new Paragraph(title, TitleFont));
        addEmptyLine(para, 2);
        document.add(para);
    }

    private static void addTableContent(PdfPTable table, String... titles) {
        PdfPCell cell;
        for (String title : titles) {
            cell = new PdfPCell(new Phrase(title));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }
}
