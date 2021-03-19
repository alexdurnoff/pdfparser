package doc;

import dialog.FileNameDialog;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void setupRegExpProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(
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

        String lineRegExp = properties.getProperty("lineRegExp");
        System.out.println(lineRegExp);
    }

    @Test
    public void testLoadPropertiesByInputStream() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(
                new FileNameDialog(
                        "Выберите файл регулярных выражений",
                        "Выбрать файл",
                        "properties"
                ).fileName()
        )){
            properties.load(fileInputStream);
            System.out.println(properties.getProperty("lineRegExp"));
        }
    }
}