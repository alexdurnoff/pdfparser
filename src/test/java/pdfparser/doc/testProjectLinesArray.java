package pdfparser.doc;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testProjectLinesArray {

    /**
     * Какой-то прогресс. В принципе, работает.
     * @throws IOException
     */
    @Test
    public void testLinesArrayPrinting() throws IOException {
        String regExp = "LS-[35]\\s[1-9,]{3,7}[\\s]";
        Pattern pattern = Pattern.compile(regExp);
        PdfReader reader = new PdfReader("/home/alexej/Документы/Работа/Мытищи/ЭОМ 47/лист 3_Схема ВРУ2-3.pdf");
        int count = reader.getNumberOfPages();
        for (int i = 1; i <= count; i++) {
            TextExtractionStrategy textExtractionStrategy = new SimpleTextExtractionStrategy();
            String text = PdfTextExtractor.getTextFromPage(reader, i, textExtractionStrategy)
                    .replace("\r\n", " ")
                    .replace('\n', ' ');
            Matcher matcher = pattern.matcher(text);
            List<String> repStrings = new ArrayList<>();
            while (matcher.find()) {
                String repString = matcher.group(0);
                if (!repStrings.contains(repString)) repStrings.add(repString);
            }
            for (String string : repStrings) {
                text = text.replace(string, string + "\n");
            }
            String[] lines = text.split("\n");
            Arrays.stream(lines).forEach(System.out::println);
            reader.close();
        }
    }
}
