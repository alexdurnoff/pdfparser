package regexp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class RegExpFromFile implements RegularExpressions{
    private final String fileName;
    private final Properties properties;
    private boolean propertiesIsLoad;

    public RegExpFromFile(String fileName){
        this.fileName = fileName;
        this.properties = new Properties();
    }


    @Override
    public String kabelRegExp() throws IOException {
        if (! propertiesIsLoad) loadProperties();
        return properties.getProperty("kabelRegExp");
    }

    @Override
    public String breakerRegExp() throws IOException {
        if (! propertiesIsLoad) loadProperties();
        return properties.getProperty("breakerRegExp");
    }

    @Override
    public String linesRegExp() throws IOException {
        if (! propertiesIsLoad) loadProperties();
        return properties.getProperty("linesRegExp");
    }

    @Override
    public String purposeRegExp() throws IOException {
        if (! propertiesIsLoad) loadProperties();
        return properties.getProperty("purposeRegExp");
    }

    private void loadProperties() throws IOException {
        properties.load(
                Files.newBufferedReader(
                        Paths.get(fileName)
                )
        );
        propertiesIsLoad = true;
    }
}
