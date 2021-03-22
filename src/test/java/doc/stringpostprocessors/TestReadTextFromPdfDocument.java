package doc.stringpostprocessors;

import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import doc.TextFromPdf;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.Charsets;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestReadTextFromPdfDocument {

    @Test
    public void testSimpleRead() throws IOException {
        TextFromPdf textFromPdf = new TextFromPdf("лист 2_Схема ВРУ1-2.pdf");
        System.out.println(textFromPdf.textFromFile());
    }

    @Test
    public void testPdfTextEtractor() throws IOException {
        PdfReader reader = new PdfReader("лист 2_Схема ВРУ1-2.pdf");
        String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
        System.out.println(textFromPage);
    }

    @Test
    public void testSimpleReadByItext() throws IOException {
        PdfReader reader = new PdfReader("лист 2_Схема ВРУ1-2.pdf");
        TextExtractionStrategy strategy = new FilteredTextRenderListener(new SimpleTextExtractionStrategy());
        byte[] buffer = reader.getPageContent(1);
        PdfReader.decodeBytes(buffer, reader.getCatalog());
        String byteString = new String(buffer, Charsets.WINDOWS_1252);
        System.out.println(byteString);
    }

    @Test
    public void testinfoByPdf() throws IOException {
        PdfReader reader = new PdfReader("лист 2_Схема ВРУ1-2.pdf");
        reader.getInfo().forEach((k,v) ->{
            System.out.println(k + "=" + v);
        });
    }

    @Test
    public void testCatclogyPdf() throws IOException {
        PdfReader reader = new PdfReader("лист 2_Схема ВРУ1-2.pdf");
        PdfDictionary catalog = reader.getCatalog();
        catalog.getKeys().forEach(k -> System.out.println(k + "=" + catalog.get(k)+" class is " + catalog.get(k).getClass()));
    }

    @Test
    public void test3() throws IOException {
        PdfReader reader = new PdfReader("лист 2_Схема ВРУ1-2.pdf");
        byte[] pageContent = reader.getPageContent(1);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pageContent);
        InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String s = bufferedReader.readLine();
        while (s != null){
            System.out.println(s);
            s = bufferedReader.readLine();
        }
    }

    @Test
    public void testReadTextByPdfBox() throws IOException {
        PDDocument document = PDDocument.load(new File("лист 2_Схема ВРУ1-2.pdf"));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);
        char[] chars = new char[text.length()];
        text.getChars(0, chars.length, chars,0);
        for (int i = 0; i <chars.length; i++){
            System.out.println(chars[i]);
        }
    }

    @Test
    public void testAfterChangeCharsetByGS() throws IOException {
        PDDocument document = PDDocument.load(new File("/home/alexej/Документы/Работа/Мытищи/ЭОМ 47/output.pdf"));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);
        System.out.println(text);
    }
}
