package doc;

import doc.stringpostprocessors.MytishchiStringPostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PdfProjectPageTest {
    private PdfProjectPage pdfProjectPage;

    @BeforeEach
    public void setUp(){
        this.pdfProjectPage = new PdfProjectPage(
                "лист 2_Схема ВРУ1-2.pdf",
                "(FR)?LS-[35]\\s[1-9,]{3,7}[\\s]",
                "-[63]{2}-[13]-[0-9]{2}\\s-[13]-[0-9]{1,3}",
                new MytishchiStringPostProcessor()
        );
    }

    @Test
    void kabelLines() throws IOException {
        System.out.println("start test method kabelLines() for klass PdfProjectPage");
        this.pdfProjectPage.kabelLines().forEach(System.out::println);
        Assertions.assertTrue(pdfProjectPage.kabelLines().size() > 0);
    }

    @Test
    void breakers() throws IOException {
        System.out.println("start test method breakers() for klass PdfProjectPage");
        this.pdfProjectPage.breakers().forEach(System.out::println);
        Assertions.assertTrue(pdfProjectPage.breakers().size() > 0);
    }

    @Test
    void lines() throws IOException {
        System.out.println("start test method lines() for klass PdfProjectPage");
        this.pdfProjectPage.lines().forEach(System.out::println);
        Assertions.assertTrue(this.pdfProjectPage.lines().size()>0);
    }

    @Test
    void fileName() {
        System.out.println("start test method lines() for klass PdfProjectPage");
        System.out.println(this.pdfProjectPage.fileName());
        Assertions.assertEquals(this.pdfProjectPage.fileName(),"лист 2_Схема ВРУ1-2.pdf");
    }
}