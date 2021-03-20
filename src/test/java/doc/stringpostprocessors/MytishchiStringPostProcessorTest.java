package doc.stringpostprocessors;

import doc.TextFromPdf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MytishchiStringPostProcessorTest {
    private StringPostProcessor postProcessor;

    @Test
    void postProcess() throws IOException {
        this.postProcessor = new PostProcessors().stringPostProcessor("Mytishchi");
        String text = new TextFromPdf("лист 2_Схема ВРУ1-2.pdf").textFromFile();
        String result = postProcessor.postProcess(text);
        System.out.println(result);
        Pattern pattern = Pattern.compile("-[63]{2}-[13]-[0-9]{2}\\s-[13]-[0-9]{1,3}[\\s]{2}-[63]{2}-[13]-[0-9]{1,3}");
        Matcher matcher = pattern.matcher(text);
        Assertions.assertTrue(matcher.find());
        matcher = pattern.matcher(result);
        Assertions.assertFalse(matcher.find());
    }
}