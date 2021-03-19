package pdfparser.doc;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testProjectLinesArray {

    /**
     * Какой-то прогресс. В принципе, работает.
     * @throws IOException
     */
    @Test
    public void testLinesArrayPrinting() throws IOException {
        String regExp = "(FR)?LS-[35]\\s[1-9,]{3,7}[\\s]";
        printLinesFromArray(regExp);
    }

    @Test
    public void testFullLinesToArrayByRegExp() throws IOException {
        String regExp = "(-[63]{2}-[13]-[0-9]{2}\\s-[13]-[0-9]{1,3}).+((FR)?LS-[35]\\s[1-9,]{3,7}[\\s])";
        String l1 = "-63-1-10 -1-16  -63-1-16  1  .2.4-4, L2  ( )-FRLS-3 1,5";
        printKabelArrayByRegExp(regExp);
    }

    @Test
    public void testBuildFullLine() throws IOException {
        List<String> fullLines = new ArrayList<>();
        String regExp = "(-[63]{2}-[13]-[0-9]{2}\\s-[13]-[0-9]{1,3}).+((FR)?LS-[35]\\s[1-9,]{3,7}[\\s])";
        Pattern pattern = Pattern.compile(regExp);
        PdfReader reader = new PdfReader("/home/alexej/Документы/Работа/Мытищи/ЭОМ 47/лист 3_Схема ВРУ2-3.pdf");
        TextExtractionStrategy textExtractionStrategy = new SimpleTextExtractionStrategy();
        int count = reader.getNumberOfPages();
        for (int i = 1; i <= count; i++){
            String text = PdfTextExtractor.getTextFromPage(reader,i, textExtractionStrategy).replace('\n', ' ');
            Matcher matcher = pattern.matcher(text);
            int number = 1;
            while (matcher.find()){
                String breaker = matcher.group(number);
                number++;
                String line = matcher.group(number);
                number++;
                String result = breaker + "==" + line;
                fullLines.add(result);
            }
        }
        System.out.println(fullLines);
    }

    @Test
    public void testKabelsToArray() throws IOException {
        String regExp = "(FR)?LS-[35]\\s[1-9,]{3,7}[\\s]";
        printKabelArrayByRegExp(regExp);
    }

    @Test
    public void testPringLongString() throws IOException {
        PdfReader reader = new PdfReader("/home/alexej/Документы/Работа/Мытищи/ЭОМ 47/лист 3_Схема ВРУ2-3.pdf");
        int count = reader.getNumberOfPages();
        for (int i = 1; i <= count; i++){
            TextExtractionStrategy textExtractionStrategy = new SimpleTextExtractionStrategy();
            String text = PdfTextExtractor.getTextFromPage(reader,i,textExtractionStrategy).replace('\n', ' ');
            System.out.println(text);
        }
    }

    private void printKabelArrayByRegExp(String regExp) throws IOException {
        List<String> kabelList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regExp);
        PdfReader reader = new PdfReader("/home/alexej/Документы/Работа/Мытищи/ЭОМ 47/лист 3_Схема ВРУ2-3.pdf");
        int count = reader.getNumberOfPages();
        for (int i = 1; i <= count; i++){
            TextExtractionStrategy textExtractionStrategy = new SimpleTextExtractionStrategy();
            String text = PdfTextExtractor.getTextFromPage(reader,i,textExtractionStrategy).replace('\n', ' ');
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()){
                String kabelLine = matcher.group(0);
                kabelList.add(kabelLine);
            }
        }
        System.out.println(kabelList);

    }

    @Test
    public void testKabelsToArrayFromProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("/home/alexej/IdeaProjects/pdfparser/src/main/resources/regExp.properties")));
        String kabelRegExp = properties.getProperty("kabelRegExp");
        printKabelArrayByRegExp(kabelRegExp);
    }

    @Test
    public void testLinesToArrayRegExpFromPropertiesFile() throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("/home/alexej/IdeaProjects/pdfparser/src/main/resources/regExp.properties")));
        String regExp = properties.getProperty("kabelRegExp");
        printLinesFromArray(regExp);
    }

    @Test
    public void testBreakersToArray() throws IOException {
        List<String> breakerLines = new ArrayList<>();
        String regExp = "-[63]{2}-[13]-[0-9]{2}\\s-[13]-[0-9]{1,3}";
        Pattern pattern = Pattern.compile(regExp);
        PdfReader reader = new PdfReader("/home/alexej/Документы/Работа/Мытищи/ЭОМ 47/лист 3_Схема ВРУ2-3.pdf");
        int count = reader.getNumberOfPages();
        for (int i = 1; i <= count; i++){
            TextExtractionStrategy textExtractionStrategy = new SimpleTextExtractionStrategy();
            String text = PdfTextExtractor.getTextFromPage(reader,i,textExtractionStrategy);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()){
                String breakerLine = matcher.group(0);
                breakerLines.add(breakerLine);
            }
        }
        System.out.println(breakerLines);
    }

    private void printLinesFromArray(String regExp) throws IOException {
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
