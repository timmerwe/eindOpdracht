package com.example.Eindproject.pdf;

import com.example.Eindproject.dto.RepairDto;
import com.example.Eindproject.dto.RepairOperationDto;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ReceiptPdfGenerator
{
    private List<RepairOperationDto> repairOperationDtos;
    private RepairDto repairDto;
    boolean isRepair;
    private double cost = 45;

    public void setRepairData(List<RepairOperationDto> repairOperationDtos, RepairDto repairDto){
        this.repairOperationDtos = repairOperationDtos;
        this.repairDto = repairDto;
        isRepair = true;
    }

    private void writeTableInfoHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Auto", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Datum", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Afspraken", font));
        table.addCell(cell);

    }

    private void writeTableInfoData(PdfPTable table) {
            table.addCell(String.valueOf(repairDto.getLicencePlate()));
            table.addCell(repairDto.getPlannedDate());
            table.addCell(repairDto.getArrangements());
    }

    private void writeTableRepairHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Handeling", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Beschrijving", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Onderdeel", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Prijs", font));
        table.addCell(cell);

    }

    private void writeTableRepairData(PdfPTable table) {
        for (RepairOperationDto repair : repairOperationDtos) {
            table.addCell(String.valueOf(repair.getCarActionTitle()));
            table.addCell(repair.getCarActionDescription());
            table.addCell(repair.getCarPartName());
            table.addCell("€" + repair.getPrice());
            cost += repair.getPrice();
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        if(isRepair) {
            Paragraph p2 = new Paragraph("Reparatie gegevens", font);
            p2.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(p2);

            PdfPTable table2 = new PdfPTable(3);
            table2.setWidthPercentage(100f);
            table2.setWidths(new float[]{2.5f, 3.5f, 2.5f});
            table2.setSpacingBefore(10);

            writeTableInfoHeader(table2);
            writeTableInfoData(table2);

            document.add(table2);

            Paragraph p = new Paragraph("Reparatie handelingen", font);
            p.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(p);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{2.5f, 3.5f, 2.5f, 1.5f});
            table.setSpacingBefore(10);

            writeTableRepairHeader(table);
            writeTableRepairData(table);

            document.add(table);
        }

        Paragraph p3 = new Paragraph("Inspectie kosten: €45,-", font);

        document.add(p3);

        Paragraph p4 = new Paragraph("Totaal ex BTW: €" + cost, font);

        document.add(p4);

        Paragraph p5 = new Paragraph("Totaal incl BTW: €" + (double) Math.round((cost * 1.21 * 100)) / 100, font);

        document.add(p5);

        document.close();

    }
}
