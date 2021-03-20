package doc;

import doc.stringpostprocessors.StringPostProcessor;

import java.io.IOException;

public interface JSONWriter {
    void writeToJSON() throws IOException;
}
