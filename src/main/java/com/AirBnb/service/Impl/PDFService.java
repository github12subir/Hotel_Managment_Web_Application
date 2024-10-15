package com.AirBnb.service.Impl;

import com.AirBnb.entity.Booking;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
@AllArgsConstructor
public class PDFService {
    private EmailService emailService;
    public void generatePDF(Booking booking)
    {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("D://booking_pdf//" + booking.getId() + "_booking_confirmation.pdf"));

            document.open();

            // Add booking confirmation header
            document.add(new Paragraph("Booking Confirmation"));

            // Add guest information
            PdfPTable guestTable = new PdfPTable(2);
            guestTable.addCell("Name:");
            guestTable.addCell(booking.getName());
            guestTable.addCell("Email:");
            guestTable.addCell(booking.getEmail());
            guestTable.addCell("Mobile:");
            guestTable.addCell(booking.getPhNumber());
            document.add(guestTable);

            // Add reservation details
            PdfPTable reservationTable = new PdfPTable(2);
            reservationTable.addCell("RoomNumber:");
            reservationTable.addCell(booking.getRoom().getRoomNumber());
            reservationTable.addCell("Location:");
            reservationTable.addCell(booking.getRoom().getProperty().getCity().getName() + ", " + booking.getRoom().getProperty().getCountry().getName());
            reservationTable.addCell("Check-in:");
            reservationTable.addCell(booking.getCheckIn().toString());
            reservationTable.addCell("Check-out:");
            reservationTable.addCell(booking.getCheckOut().toString());
            reservationTable.addCell("Total-Guests:");
            reservationTable.addCell(String.valueOf(booking.getTotalNoOfGuest()));
            reservationTable.addCell("Total Price:");
            reservationTable.addCell(String.valueOf(booking.getTotalPrice()));
            document.add(reservationTable);

            // Add payment information
            PdfPTable paymentTable = new PdfPTable(2);
            paymentTable.addCell("Payment Method:");
            paymentTable.addCell("Credit Card"); // Hardcoded for now, update with actual payment method
            paymentTable.addCell("Amount Paid:");
            paymentTable.addCell(String.valueOf(booking.getTotalPrice()));
            paymentTable.addCell("Payment Date:");
            paymentTable.addCell(booking.getCheckIn().toString()); // Assuming payment date is the same as check-in date
            document.add(paymentTable);

            // Add cancellation policy
            document.add(new Paragraph("Cancellation Policy:"));
            document.add(new Paragraph("Guests can cancel their reservation up to 7 days before check-in for a full refund."));

            document.close();

            //another method to call email
            emailService.sendEmailWithAttachment(
                    booking.getEmail(),
                    "Booking Confirmation.Your booking id is"+booking.getId(),
                    "test",
                            new File("D://booking_pdf//" + booking.getId() + "_booking_confirmation.pdf"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





//    {
//        try {
//
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream("C://booking_pdf//"+booking.getId()+"_booking_confirmation.pdf"));
//
//        document.open();
//
//        PdfPTable table = new PdfPTable(3);
//        addTableHeader(table);
//        addRows(table,booking);
//       // addCustomRows(table);
//
//        document.add(table);
//        document.close();
//    }catch (Exception e)
//    {
//        e.printStackTrace();
//    }
//    }
//    private void addTableHeader(PdfPTable table) {
//        Stream.of("Guest_Name", "Hotel Name", "City")
//                .forEach(columnTitle -> {
//                    PdfPCell header = new PdfPCell();
//                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                    header.setBorderWidth(2);
//                    header.setPhrase(new Phrase(columnTitle));
//                    table.addCell(header);
//                });
//    }
//    private void addRows(PdfPTable table,Booking booking) {
//        table.addCell(booking.getGuestName());
//        table.addCell(booking.getProperty().getName());
//        table.addCell(booking.getProperty().getCity().getName());
//    }
}
