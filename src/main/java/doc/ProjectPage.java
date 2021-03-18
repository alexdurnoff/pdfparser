package doc;

import java.io.IOException;
import java.lang.String;

import java.util.List;

public interface ProjectPage {
    List<String> lines() throws IOException;
    List<String> breakers() throws IOException;
}
