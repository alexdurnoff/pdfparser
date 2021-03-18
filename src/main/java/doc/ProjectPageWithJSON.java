package doc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Инкапсулирует ProjectPage. Записывает в JSON.
 * Декоратор.
 */
public class ProjectPageWithJSON implements ProjectPage, JSONWriter{
    private final ProjectPage projectPage;

    public ProjectPageWithJSON(ProjectPage projectPage){
        this.projectPage = projectPage;
    }

    @Override
    @JsonProperty
    public List<String> lines() throws IOException {
        return this.projectPage.lines();
    }

    @Override
    @JsonProperty
    public List<String> breakers() throws IOException {
        return this.projectPage.breakers();
    }

    @Override
    public String fileName() {
        return this.projectPage.fileName();
    }

    @Override
    public void writeToJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(fileName() + ".json"), this);
    }
}
