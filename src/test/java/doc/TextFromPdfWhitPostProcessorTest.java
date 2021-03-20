package doc;

import doc.stringpostprocessors.MytishchiStringPostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class TextFromPdfWhitPostProcessorTest {

    @Test
    void textFromFile() throws IOException {
        TextFromPdfWhitPostProcessor textFromPdfWhitPostProcessor = new TextFromPdfWhitPostProcessor(
                new TextFromPdf("лист 2_Схема ВРУ1-2.pdf"),
                new MytishchiStringPostProcessor()
        );
        String text = textFromPdfWhitPostProcessor.textFromFile();
        System.out.println(text);
        Pattern pattern = Pattern.compile("-[63]{2}-[13]-[0-9]{2}\\s-[13]-[0-9]{1,3}[\\s]{2}-[63]{2}-[13]-[0-9]{1,3}");
        Matcher matcher = pattern.matcher(text);
        Assertions.assertFalse(matcher.find());
    }
}