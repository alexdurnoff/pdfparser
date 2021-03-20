package doc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class LineListSplitByBreakerRegExpTest {
    private LineListSplitByBreakerRegExp list;
    private String text;
    private final String breakerRegExp = "-[63]{2}-[13]-[0-9]{2}\\s";


    @Test
    void linesSplitByRegExp() throws IOException {
        this.text = new TextFromPdf("лист 2_Схема ВРУ1-2.pdf").textFromFile();
        this.list = new LineListSplitByBreakerRegExp(
                text,
                breakerRegExp
        );
        this.list.linesSplitByRegExp().forEach(System.out::println);
        this.list.linesSplitByRegExp().forEach(s -> {
            Assertions.assertTrue(s.startsWith("-63") || s.startsWith("14") || s.equals(""));
        });
    }
}