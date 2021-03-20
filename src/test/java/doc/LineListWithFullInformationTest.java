package doc;

import org.junit.jupiter.api.Test;

import java.io.IOException;


class LineListWithFullInformationTest {
    private LineListWithFullInformation list;
    private String text;

    @Test
    void linesWithFullInformation() throws IOException {
        this.text = new TextFromPdf("лист 2_Схема ВРУ1-2.pdf").textFromFile();
        this.list = new LineListWithFullInformation(
                text,
                "(FR)?LS-[35]\\s[1-9,]{3,7}[\\s]"
        );
        this.list.linesWithFullInformation().forEach(System.out::println);
    }
}