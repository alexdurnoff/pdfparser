package doc;



import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfProjectPage implements ProjectPage{
    private final String fileName;
    private PdfReader reader;
    private final String lineRegExp;
    private final String breakerRegExp;

    public PdfProjectPage(String fileName, String lineRegExp, String breakerRegExp) {
        this.fileName = fileName;
        this.lineRegExp = lineRegExp;
        this.breakerRegExp = breakerRegExp;
    }

    @Override
    public List<String> lines() throws IOException {
        Pattern pattern = Pattern.compile(lineRegExp);
        return stingListByRegExp(pattern);
    }

    private List<String> stingListByRegExp(Pattern pattern) throws IOException {
        if (this.reader == null) this.reader = new PdfReader(fileName);
        List<String> stringList = new ArrayList<>();
        String text = PdfTextExtractor.getTextFromPage(
                reader,
                1,
                new SimpleTextExtractionStrategy()
        );
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            stringList.add(matcher.group(0));
        }
        return stringList;
    }


    @Override
    public List<String> breakers() throws IOException {
        Pattern pattern = Pattern.compile(breakerRegExp);
        return stingListByRegExp(pattern);
    }
}
