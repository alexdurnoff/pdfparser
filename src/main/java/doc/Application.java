package doc;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import dialog.FileNameDialog;
import dialog.FileNamesDialog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private final static Logger logger = LogManager.getLogger(Application.class);


    public static void main(String[] args){
        try{
            setupRegExpProperties();
            List<String> fileNames = new FileNamesDialog(
                    "Выбрать исходные pdf-файлы",
                    "Выбрать файлы",
                    "pdf"
            ).fileNames();
            for (String fileName : fileNames){
                List<String> pagesFileNames = new PdfDocumentSplitByPages(fileName).onePagePdfFiles();
                logger.info("pagesFileNames size is" + pagesFileNames.size());
                for (String pagesFileName : pagesFileNames) {
                    logger.info(pagesFileName);
                    new ProjectPageWithJSON(
                            new PdfProjectPage(
                                    pagesFileName,
                                    regExpProperties.getProperty("kabelRegExp"),
                                    regExpProperties.getProperty("breakerRegExp")
                            )
                    ).writeToJSON();
                }
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }

    protected static void setupRegExpProperties() throws IOException {
        regExpProperties.load(
                Files.newBufferedReader(
                        Paths.get(
                                new FileNameDialog(
                                        "Выберите файл регулярных выражений",
                                        "Выбрать файл",
                                        "properties"
                                ).fileName()
                        )
                )
        );
    }



}
