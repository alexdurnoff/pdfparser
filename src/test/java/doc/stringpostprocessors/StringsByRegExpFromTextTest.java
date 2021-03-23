package doc.stringpostprocessors;

import doc.TextFromPdf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class StringsByRegExpFromTextTest {
    private StringsByRegExpFromText strings;
    private String text;
    private final Pattern pattern = Pattern.compile("(FR)?LS-[35]\\s[1-9,]{3,7}[\\s]");

    @BeforeEach
    public void setUp() throws IOException {
        this.text = new TextFromPdf("лист 2_Схема ВРУ1-2.pdf").textFromFile();
        this.strings = new StringsByRegExpFromText(pattern, text);
    }

    @Test
    void strings() {
        List<String> strings = this.strings.strings();
        Assertions.assertTrue(strings.size()>0);
        strings.forEach(System.out::println);
    }
}