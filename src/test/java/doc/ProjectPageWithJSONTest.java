package doc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectPageWithJSONTest {
    private ProjectPageWithJSON projectPageWithJSON = new ProjectPageWithJSON(new ProjectPage.TestPage());

    @Test
    void lines() throws IOException {
        List<String> lines = projectPageWithJSON.lines();
        Assertions.assertEquals(lines.size(), 3);
        Assertions.assertEquals(lines.get(0), "ВВГнг-LS 3х2,5");
        Assertions.assertEquals(lines.get(1), "ППГнг-LS 3х4");
        Assertions.assertEquals(lines.get(2), "NYM 5х6");
    }

    @Test
    void breakers() throws IOException {
        List<String> breakers = projectPageWithJSON.breakers();
        Assertions.assertEquals(breakers.size(), 3);
        Assertions.assertEquals(breakers.get(0), "IEK ВА47-29");
        Assertions.assertEquals(breakers.get(1), "ABB S203");
        Assertions.assertEquals(breakers.get(2), "IC60N");
    }

    @Test
    void fileName() {
        String fileName = projectPageWithJSON.fileName();
        Assertions.assertEquals(fileName,"/home/alexej/тестовый файл");
    }

    @Test
    void writeToJSON() throws IOException {
        projectPageWithJSON.writeToJSON();
    }
}