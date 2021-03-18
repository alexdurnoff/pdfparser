package pdfparser.doc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class TestPdfBoxFeatures {
    private File pdfFile = new File("10-А-11_А3.pdf");
    private  PDDocument document;

    @Test
    public void testPdfBoxFeatures() throws IOException {
        this.document = PDDocument.load(this.pdfFile);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document).replace('\n', ' ');
        System.out.println(text);
        document.close();
    }

    @Test
    public void testPdfBoxKalugaMultiPageWriteToFiles() throws IOException {
        this.document = PDDocument.load(new File("Т20-ИОС1.2-РД.pdf"));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        PDPageTree pages = this.document.getPages();
        AtomicInteger number = new AtomicInteger(1);
        pages.forEach(pdPage -> {
            try {
                InputStream contents = pdPage.getContents();
                OutputStream outputStream = new FileOutputStream(document.getDocumentInformation().getTitle() + "page number " + number);
                byte[] buffer = new byte[10000000];
                while (contents.read(buffer) > 0){
                    outputStream.write(buffer);
                }
                contents.close();
                outputStream.close();
                number.getAndIncrement();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Этот тест пройден. Таким образом можно дегко разбить документ на страницы.
     * Надо, правда, как-то название нормальное дать... А то пока тупо по номеру страницы.
     * @throws IOException - бросается исключением, если что не так при чтении/записи на диск.
     */
    @Test
    public void testPDPageFeatures() throws IOException {
        this.document = PDDocument.load(new File("Т20-ИОС1.2-РД.pdf"));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        int size = this.document.getNumberOfPages();
        for (int i = 0; i < size; i++){
            PDPage page = this.document.getPage(i);
            PDDocument doc = new PDDocument();
            doc.addPage(page);

            doc.save(document.getDocumentInformation().getTitle() + " page number " + i + ".pdf");
            doc.close();
        }
    }
}
