package doc;

import java.io.IOException;
import java.lang.String;

import java.util.ArrayList;
import java.util.List;

public interface ProjectPage {
    List<String> lines() throws IOException;
    List<String> breakers() throws IOException;
    String fileName();

    /**
     * The test implementation of interface.
     */
    static final class TestPage implements ProjectPage{
        private List<String> lines = new ArrayList<>();
        private List<String> breakers = new ArrayList<>();

        public TestPage(){
            lines.add("ВВГнг-LS 3х2,5");
            lines.add("ППГнг-LS 3х4");
            lines.add("NYM 5х6");
            breakers.add("IEK ВА47-29");
            breakers.add("ABB S203");
            breakers.add("IC60N");

        }


        @Override
        public List<String> lines() throws IOException {
            return this.lines;
        }

        @Override
        public List<String> breakers() throws IOException {
            return this.breakers;
        }

        @Override
        public String fileName() {
            return "/home/alexej/тестовый файл";
        }
    }
}
