package doc;

import java.io.IOException;

public class TextFromTxt implements TextFromFile{
    private final String fileName;


    public TextFromTxt(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String textFromFile() throws IOException {
        return null;
    }
}
