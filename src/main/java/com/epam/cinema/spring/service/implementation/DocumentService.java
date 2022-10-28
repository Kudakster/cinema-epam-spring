package com.epam.cinema.spring.service.implementation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Service
public class DocumentService {
    @Autowired
    private TicketService ticketService;

    private Document document;

    @SneakyThrows
    public void writeStatistics(HttpServletResponse response) {
        document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        AddTitle();
        AddTable();
        document.close();
    }

    private void AddTitle() throws DocumentException {
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Paragraph paragraph = new Paragraph("Tickets Sold", font);
        document.add(paragraph);
    }

    private void AddTable() throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setPaddingTop(10f);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        float[] columnWidths = {1f, 1f, 1f};
        table.setWidths(columnWidths);

        table.addCell("Day");
        table.addCell("Week");
        table.addCell("Month");

        long ticketSoldByDay = ticketService.countTicketByDate(LocalDate.now());
        long ticketSoldByWeek = ticketService.countTicketByDate(LocalDate.now().minusWeeks(1));
        long ticketSoldByMonth = ticketService.countTicketByDate(LocalDate.now().minusMonths(1));

        table.addCell(Long.toString(ticketSoldByDay));
        table.addCell(Long.toString(ticketSoldByWeek));
        table.addCell(Long.toString(ticketSoldByMonth));

        document.add(table);
    }
}
