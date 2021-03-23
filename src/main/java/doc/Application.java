package doc;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import dialog.FileNameDialog;
import dialog.FileNamesDialog;
import doc.stringpostprocessors.PostProcessorFromFile;
import doc.stringpostprocessors.PostProcessors;
import doc.stringpostprocessors.StringPostProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import regexp.RegExpFromFile;
import regexp.RegularExpressions;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public final static Properties regExpProperties = new Properties();
    public static RegularExpressions regularExpressions;
    private final static Logger logger = LogManager.getLogger(Application.class);
    private static String fileName;


    public static void main(String[] args){
        try{
            fileName = new FileNameDialog(
                    "Выберите файл регулярных выражений",
                    "Выбрать файл",
                    "properties"
            ).fileName();
            setupRegExpProperties();
            List<String> fileNames = new FileNamesDialog(
                    "Выбрать исходные pdf-файлы",
                    "Выбрать файлы",
                    "pdf"
            ).fileNames();
            StringPostProcessor postProcessor = new PostProcessorFromFile(fileName).postProcessor();
            RegularExpressions regularExpressions = new RegExpFromFile(fileName);
            for (String fileName : fileNames){
                List<String> pagesFileNames = new PdfDocumentSplitByPages(fileName).onePagePdfFiles();
                for (String pagesFileName : pagesFileNames) {
                    new ProjectPageWithJSON(
                            new PdfProjectPage(
                                    pagesFileName,
                                    regularExpressions.kabelRegExp(),
                                    regularExpressions.linesRegExp()
                            )
                    ).writeToJSON();
                }
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }

    protected static void setupRegExpProperties() throws IOException {
        regularExpressions = new RegExpFromFile(fileName);
    }



}
