package com.example.prhack.converters;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// pdf to txt
public class ExtractTextPdf implements Runnable{

    public void start() throws Exception {
        PDDocument document = PDDocument.load(new File("/home/riki/PromrelationBankHack/dataset/Тестовый dataset/ПАО НКХП 2315014748/Финансовое досье/2020/4 квартал/Бухгалтерская отчетность/Форма 1.pdf"));
        String text = extractTextFromScannedDocument(document);
        System.out.println(text);
    }

    private String extractTextFromScannedDocument(PDDocument document) throws IOException, TesseractException {

        // Extract images from file
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        StringBuilder out = new StringBuilder();

        ITesseract _tesseract = new Tesseract();
        _tesseract.setDatapath("/home/riki/PromrelationBankHack/tessdata");
        _tesseract.setLanguage("rus");

        for (int page = 0; page < document.getNumberOfPages(); page++) {
            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

            // Create a temp image file
            File tempFile = File.createTempFile("tempfile_" + page, ".png");
            ImageIO.write(bufferedImage, "png", tempFile);

            String result = _tesseract.doOCR(tempFile);
            out.append(result);

            // Delete temp file
            tempFile.delete();

        }

        return out.toString();

    }


    @Override
    public void run() {
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}