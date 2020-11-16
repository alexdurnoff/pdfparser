package pdfparser.doc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class TestPdfBoxFeatures {
    private File pdfFile = new File("10-А-11_А3.pdf");
    private  PDDocument document;

    @Test
    public void testPdfBoxFeatures() throws IOException {
        this.document = PDDocument.load(this.pdfFile);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        System.out.println(text);
        document.close();
    }
}
