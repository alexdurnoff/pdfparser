package doc.stringpostprocessors;

import jdk.tools.jlink.internal.PostProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PostProcessorFromFile {
    private final String fileName;

    public PostProcessorFromFile(String fileName){
        this.fileName = fileName;
    }

    public StringPostProcessor postProcessor() throws IOException {
        Properties properties = new Properties();
        properties.load(
                Files.newBufferedReader(
                        Paths.get(fileName)
                )
        );
        return new PostProcessors().stringPostProcessor(properties.getProperty("postProcessor"));
    }
}
