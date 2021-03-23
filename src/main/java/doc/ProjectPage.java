package doc;

import doc.stringpostprocessors.StringPostProcessor;

import java.io.IOException;
import java.lang.String;

import java.util.ArrayList;
import java.util.List;

public interface ProjectPage {
    List<String> kabelLines() throws IOException;
    List<String> breakers() throws IOException;
    List<String> lines() throws IOException;
    List<String> purposes() throws IOException;
    String fileName() throws IOException;

    /**
     * The test implementation of interface.
     */
    static final class TestPage implements ProjectPage{
        /**
         * This method should return List of kabel lines from Page;
         */
        private List<String> kabels = new ArrayList<>();

        /**
         * This method should return List of breakers from Page;
         */
        private List<String> breakers = new ArrayList<>();

        /**
         * This method should return List of line which included both kabel lines and breakers.
         */
        private List<String> lines = new ArrayList<>();

        public TestPage(){
            kabels.add("ВВГнг-LS 3х2,5");
            kabels.add("ППГнг-LS 3х4");
            kabels.add("NYM 5х6");
            breakers.add("IEK ВА47-29");
            breakers.add("ABB S203");
            breakers.add("IC60N");

        }


        @Override
        public List<String> kabelLines() throws IOException {
            return this.kabels;
        }

        @Override
        public List<String> breakers() throws IOException {
            return this.breakers;
        }

        @Override
        public List<String> lines() {
            return this.lines;
        }

        @Override
        public List<String> purposes() throws IOException {
            List<String> purposes = new ArrayList();
            purposes.add("Группа 1");
            purposes.add("Группа 2");
            purposes.add("Группа 3");
            return purposes;
        }

        @Override
        public String fileName() {
            return "/home/alexej/тестовый файл";
        }
    }
}
