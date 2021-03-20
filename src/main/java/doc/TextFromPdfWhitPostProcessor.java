package doc;

import doc.stringpostprocessors.StringPostProcessor;

import java.io.IOException;

/**
 * Класс инкапсулирует TextFromFile и StringPostProcessor. Дополнительно обрабатывает текст,
 * полученный из файла.
 */
public class TextFromPdfWhitPostProcessor implements TextFromFile{
    private final TextFromPdf textFromPdf;
    private final StringPostProcessor postProcessor;


    public TextFromPdfWhitPostProcessor(TextFromPdf textFromPdf, StringPostProcessor postProcessor){
        this.textFromPdf = textFromPdf;
        this.postProcessor = postProcessor;
    }

    @Override
    public String textFromFile() throws IOException {
        String text = this.textFromPdf.textFromFile();
        return this.postProcessor.postProcess(text);
    }

}
