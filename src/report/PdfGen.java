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
import entity.BuyInsurance;
import entity.ReserveEquipment;
import entity.Vehicle;
import entity.VehicleClass;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provide function that generate pdf report.
 */
public class PdfGen {

    private static DateFormat df = new SimpleDateFormat("yyyy");

    private static DateFormat tf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    private static Font TitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font ContentFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL);

    /**
     * Generate daily rental report.
     *
     * @param vlist Arraylist of {@link Vehicle} to be shown in report.
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public static void genDailyRentalReport(ArrayList<Vehicle> vlist) throws DocumentException, FileNotFoundException {
        String title = "Daily Rental Report " + getDate();

        genVehicleReport(vlist, title);

    }

    /**
     * Generate daily return report.
     *
     * @param vlist Arraylist of {@link Vehicle} to be shown in report.
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public static void genDailyReturnReport(ArrayList<Vehicle> vlist) throws DocumentException, FileNotFoundException {
        String title = "Daily Return Report " + getDate();

        genVehicleReport(vlist, title);
    }

    /**
     * Generate report for not returned vehicles.
     *
     * @param notReturnList ArrayList of {@link Reservation} that has not been
     * returned.
     * @param title The title of the report.
     */
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

    /**
     * Generate report for available vehicle class
     *
     * @param vcList ArrayList of available {@link VehicleClass}.
     * @param title Title of the report.
     */
    public static void genAvailableVehicleClassReport(ArrayList<VehicleClass> vcList, String title) {
        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(title + ".pdf"));
            document.open();

            document.newPage();
            addMetaData(document, title);
            addTitle(document, title);

            PdfPTable table = new PdfPTable(5);
            addTableContent(table, "Vehicle Type", "Vehicle Class",
                    "Hourly Rate", "Daily Rate", "Weekly Rate");
            for (VehicleClass vc : vcList) {
                addTableContent(table, vc.getVehicleType().toString(), vc.getClassName(),
                        vc.getHourlyPrice(), vc.getDailyPrice(), vc.getWeeklyPrice());
            }
            document.add(table);

            document.close();
        } catch (Exception ex) {
            Logger.getLogger(PdfGen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Generate report for vehicle for sale.
     *
     * @param vlist ArrayList of for sale {@link Vehicle}.
     * @param title Title of the report.
     */
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

    /**
     * Generate report for not rented reservation.
     *
     * @param resList ArrayList of not rented {@link Reservation}.
     * @param title Title of the report.
     */
    public static void genUnrentedReservation(ArrayList<Reservation> resList, String title) {
        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(title + ".pdf"));
            document.open();

            document.newPage();
            addMetaData(document, title);
            addTitle(document, title);

            PdfPTable table = new PdfPTable(6);
            addTableContent(table, "Reservation #", "Vehicle Class",
                    "Pickup Date", "Return Date", "Customer Name");
            for (Reservation res : resList) {
                addTableContent(table, res.getReservationNo(), res.getVehicleClass(),
                        tf.format(res.getPickupTime()), tf.format(res.getReturnTime()),
                        res.getCustomerName());
            }
            document.add(table);

            document.close();
        } catch (Exception ex) {
            Logger.getLogger(PdfGen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Generate report for all vehicle list.
     *
     * @param vlist ArrayList of {@link Vehicle} to display in report.
     * @param title Title of the report.
     */
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

    /**
     * Generate report for vehicle list that used in daily rent and daily
     * return.
     *
     * @param vlist ArrayList of {@link Vehicle} to display in report.
     * @param title Title of the report.
     */
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

    /**
     * Generate reservation confirmation.
     * @param res The {@link Reservation} to generate confirmation.
     * @param title Title of the report.
     */
    public static void genReservationConfirmation(Reservation res, String title) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(title + ".pdf"));
            document.open();

            document.newPage();
            addMetaData(document, title);
            addTitle(document, title);

            addParagraph(document, "Your Reservation number is : " + res.getReservationNo());
            addParagraph(document, "Pickup Time: " + res.getPickupTime().toString());
            addParagraph(document, "Return Time: " + res.getReturnTime().toString());
            addParagraph(document, "Vehicle Class: " + res.getVehicleClass());

            addEmptyLine(document, 1);

            addLine(document, "Additional Equipment:");
            for (ReserveEquipment re : res.getReserveEquipment()) {
                addLine(document, "        " + re.getEquipmentType());
            }

            addLine(document, "Insurance: ");
            for (BuyInsurance insurance : res.getReserveInsurance()) {
                addLine(document, "        " + insurance.getInsuranceName());
            }

            addParagraph(document, "Your total cost will be: " + res.getEstimation());

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

    private static void addEmptyLine(Document document, int number) throws DocumentException {
        Paragraph para = new Paragraph();
        for (int i = 0; i < number; i++) {
            para.add(new Paragraph(" "));
        }
        document.add(para);
    }

    private static void addTitle(Document document, String title) throws DocumentException {
        Paragraph para = new Paragraph();
        addEmptyLine(para, 1);
        para.add(new Paragraph(title, TitleFont));
        addEmptyLine(para, 2);
        document.add(para);
    }

    private static void addParagraph(Document document, String content) throws DocumentException {
        Paragraph para = new Paragraph();
        addEmptyLine(para, 1);
        para.add(new Paragraph(content, ContentFont));
        addEmptyLine(para, 1);
        document.add(para);
    }

    private static void addLine(Document document, String content) throws DocumentException {
        Paragraph para = new Paragraph();
        para.add(new Paragraph(content, ContentFont));
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
