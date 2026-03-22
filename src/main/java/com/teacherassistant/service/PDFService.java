package com.teacherassistant.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@Service
public class PDFService {

    public byte[] generatePdf(Map<String, Object> questionsMap) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Worksheet"));
        document.add(new Paragraph("\n"));

        List<Map<String, String>> questions =
                (List<Map<String, String>>) questionsMap.get("questions");

        int index = 1;

        for (Map<String, String> q : questions) {
            document.add(new Paragraph(index + ". " + q.get("question")));
            document.add(new Paragraph("Answer: " + q.get("answer")));
            document.add(new Paragraph("\n"));
            index++;
        }

        document.close();

        return out.toByteArray();
    }

}
