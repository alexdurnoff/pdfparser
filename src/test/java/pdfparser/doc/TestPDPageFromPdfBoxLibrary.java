package pdfparser.doc;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestPDPageFromPdfBoxLibrary {

    @Test
    public void testMetaDFata() throws IOException {
        PDDocument document = PDDocument.load(new File("Т20-ИОС1.2-РД.pdf"));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String title = document.getDocumentInformation().getTitle();
        int size = document.getNumberOfPages();
        for (int i = 0; i < size; i++){
            PDPage page = document.getPage(i);
            PDDocument doc=new PDDocument();
            doc.addPage(page);
            String text = pdfTextStripper.getText(doc).replace('\n', ' ');
            System.out.println(text);
            doc.close();
        }
    }

    @Test
    public void testExtractOnePageTextFromDocxument() throws IOException {
        PDDocument document = PDDocument.load(new File("Т20-ИОС1.2-РД.pdf"));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        PDPage page = document.getPage(23);
        PDDocument doc = new PDDocument();
        doc.addPage(page);
        String text = pdfTextStripper.getText(doc).replace('\n', ' ');
        doc.close();
        System.out.println(text);

    }

    @Test
    public void testOnePageDocumentExtractText() throws IOException {
        PDDocument document = PDDocument.load(new File("BANYA U OZERA_ 2020-11-10 page number 23.pdf"));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document).replace('\n', ' ');
        Files.newBufferedWriter(Paths.get("page 23.txt")).write(text);
        System.out.println(text);
    }

    @Test
    public void testPDPageContentStream() throws IOException {
        PDDocument document = PDDocument.load(new File("Т20-ИОС1.2-РД.pdf"));
        int size = document.getNumberOfPages();
        for (int i = 0; i < size; i++){
            PDPage page = document.getPage(i);
            COSDictionary cosObject = page.getCOSObject();
        }

    }
}
