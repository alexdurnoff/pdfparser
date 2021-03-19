package doc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс инкапсулирует путь к pdf-документу.
 * Создает на диске несколько одностраничных pdf-файлов.
 * Возвращает список строк-путей к этим файлам.
 */
public class PdfDocumentSplitByPages implements Pages{
    private final String filePath;
    private final File file;


    public PdfDocumentSplitByPages(String filePath){
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    @Override
    public List<String> onePagePdfFiles() throws IOException {
        List<String> pdfFiles = new ArrayList<>();
        PDDocument document = PDDocument.load(file);
        int size = document.getNumberOfPages();
        if (size == 1) {
            pdfFiles.add(filePath);
        } else {
            for (int i = 0; i < size; i++){
                PDPage page = document.getPage(i);
                PDDocument doc = new PDDocument();
                doc.addPage(page);
                String pageFileName =
                        filePath
                                + " page " + i + ".pdf";
                doc.save(pageFileName);
                doc.close();
                pdfFiles.add(pageFileName);
            }
        }
        document.close();
        return pdfFiles;
    }
}
