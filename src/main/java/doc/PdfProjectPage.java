package doc;



import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import doc.stringpostprocessors.DefaultStringPostProcessor;
import doc.stringpostprocessors.PostProcessors;
import doc.stringpostprocessors.StringPostProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс фактически инкапсулирует одностраничный pdf-документ.
 */
public class PdfProjectPage implements ProjectPage{
    private final String fileName;
    private PdfReader reader;
    private final String lineRegExp;
    private final String breakerRegExp;
    private final static Logger logger = LogManager.getLogger(PdfProjectPage.class);
    private final StringPostProcessor postProcessor;

    public PdfProjectPage(String fileName, String lineRegExp, String breakerRegExp) {
        this.fileName = fileName;
        this.lineRegExp = lineRegExp;
        this.breakerRegExp = breakerRegExp;
        this.postProcessor = new DefaultStringPostProcessor();
    }

    /**
     * Этот конструктор принримает stringPostProcessor, если нужна
     * дополниьтельная обработка текста после его получения из файла.
     * @param fileName - путь к pdf-файлу.
     * @param lineRegExp - регулярное выражения для кабельных линий.
     * @param breakerRegExp - регулярное выражение для автоматов.
     * @param postProcessor - StringPostProcessor для обработки текста.
     */
    public PdfProjectPage(String fileName, String lineRegExp, String breakerRegExp, StringPostProcessor postProcessor){
        this.fileName = fileName;
        this.lineRegExp = lineRegExp;
        this.breakerRegExp = breakerRegExp;
        this.postProcessor = postProcessor;
    }

    /**
     * Метод возвращает список кабельных линий.
     * @return - список автомато breakers.
     * @throws IOException - бросает исключение, если что-то пошло не так при записи/чтении  с диска.
     */
    @Override
    public List<String> kabelLines() throws IOException {
        Pattern pattern = Pattern.compile(lineRegExp);
        return stingListByRegExp(pattern);
    }

    /**
     * Метод возвращает список строк по паттерну.
     * @param pattern - паттерн, заранее созданный по регулярному выражению.
     * @return - список строк, найденных в тексте по паттерну.
     * @throws IOException - бросает исключение, если что-то пошло не так при записи/чтении  с диска.
     */
    private List<String> stingListByRegExp(Pattern pattern) throws IOException {
        List<String> stringList = new ArrayList<>();
        String text = text();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            stringList.add(matcher.group(0));
        }
        return stringList;
    }

    /**
     * This method return text from pdfPage.
     * @return String text.
     * @throws IOException
     */
    private String text() throws IOException {
        return new TextFromPdfWhitPostProcessor(
                new TextFromPdf(fileName),
                postProcessor
        ).textFromFile();
    }


    /**
     * Метод возвращает список автоматов.
     * @return - список автомато breakers.
     * @throws IOException - бросает исключение, если что-то пошло не так при записи/чтении  с диска.
     */
    @Override
    public List<String> breakers() throws IOException {
        Pattern pattern = Pattern.compile(breakerRegExp);
        return stingListByRegExp(pattern);
    }

    /**
     * Метод возвращает список строк, которые содержат полную инфоромацию о линии:
     * автомат + кабель + фазировка и т.п.
     * @return список линий lineList.
     */
    @Override
    public List<String> lines() throws IOException {
        List<String> lineList = new ArrayList<>();
        List<String> textSplitByBreakers = new LineListSplitByBreakerRegExp(
                text(),
                breakerRegExp
        ).linesSplitByRegExp();
        for (String str : textSplitByBreakers) {
            lineList.addAll(
                    new LineListWithFullInformation(
                            str,
                            lineRegExp
                    ).linesWithFullInformation()
            );
        }
        return lineList;
    }

    /**
     * This method split String by kabels regular expression.
     * @return String result
     */
    private List<String> textSplitByKabels(String text) throws IOException {
        String result = text;
        List<String> checkedStrings = new ArrayList<>();
        Pattern pattern = Pattern.compile(lineRegExp);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            String replacedString  = matcher.group(0);
            if (!checkedStrings.contains(replacedString)){
                result = result.replace(replacedString, replacedString + "\n");
                checkedStrings.add(replacedString);
            }
        }
        return Arrays.asList(result.split("\n"));
    }

    /**
     * This method split text from pdf page by breaker regular expression.
     * @return String result.
     * @throws IOException - throw IOException if somephing wrong with i/o.
     */
    private List<String> textSplitByBreakers() throws IOException {
        String text = text();
        String result = text;
        List<String> checkedStrings = new ArrayList<>();
        Pattern pattern = Pattern.compile(breakerRegExp);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            String replacedString  = matcher.group(0);
            logger.info(replacedString);
            if (!checkedStrings.contains(replacedString)){
                result = result.replace(replacedString, "\n" + replacedString);
                checkedStrings.add(replacedString);
            }
        }
        return Arrays.asList(result.split("\n").clone());
    }

    /**
     * Метод возвращает строковое представление пути к файлу.
     * @return
     */
    @Override
    public String fileName() {
        return this.fileName;
    }


}
