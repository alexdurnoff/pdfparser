package doc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс инкапсулирует путь к pdf-документу.
 * Создает на диске несколько одностраничных pdf-файлов.
 * Возвращает список строк-путей к этим файлам.
 */
public class PdfDocumentSplitByPages implements Pages{
    private final String fileName;

    public PdfDocumentSplitByPages(String fileName){
        this.fileName = fileName;
    }

    @Override
    public List<String> onePagePdfFiles() throws IOException {
        List<String> pdfFiles = new ArrayList<>();
        PDDocument document = PDDocument.load(new File(fileName));
        int size = document.getNumberOfPages();
        for (int i = 0; i < size; i++){
            PDPage page = document.getPage(i);
            PDDocument doc = new PDDocument();
            doc.addPage(page);
            String pageFileName = document.getDocumentInformation().getTitle() + " page number " + i + ".pdf";
            doc.save(pageFileName);
            doc.close();
            pdfFiles.add(pageFileName);
        }
        return pdfFiles;
    }
}
