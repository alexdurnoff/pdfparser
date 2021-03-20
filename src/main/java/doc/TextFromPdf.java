package doc;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.IOException;

/**
 * Тестовый класс. Инкапсулирует путь к pdf-файлу.
 * Возвращает текст из pdf-файла в виде строки.
 */
public class TextFromPdf implements TextFromFile{
    private final String fileName;

    public TextFromPdf(String fileName){
        this.fileName = fileName;
    }

    @Override
    public String textFromFile() throws IOException {
        PdfReader reader = new PdfReader(fileName);
        TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
        String string = PdfTextExtractor
                .getTextFromPage(reader,1,strategy)
                .replace("\n", " ");
        return string;
    }
}
