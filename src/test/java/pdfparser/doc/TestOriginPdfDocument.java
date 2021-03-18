package pdfparser.doc;

import doc.OriginPdfDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

public class TestOriginPdfDocument {

    @Test
    public void testOriginPdfDocument(){
        OriginPdfDocument document = new OriginPdfDocument();
        System.out.println(document.fileName());
    }

    /**
     * На слабом ноуте забарахлил junit почему-то.
     * Возможно, Maven не до конца все скачал.
     * Поэтому изобразил main.
     */
    public static void main(String[] args) {
        OriginPdfDocument document = new OriginPdfDocument();
        System.out.println(document.fileName());
    }

}
