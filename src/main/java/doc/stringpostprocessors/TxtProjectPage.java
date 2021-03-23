package doc.stringpostprocessors;

import doc.ProjectPage;
import doc.TextFromTxt;
import jdk.internal.joptsimple.internal.Strings;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class TxtProjectPage implements ProjectPage {
    private final String fileName;
    private final String lineRegExp;
    private final String breakerRegExp;


    public TxtProjectPage(String fileName, String lineRegExp, String breakerRegExp){
        this.fileName = fileName;
        this.lineRegExp = lineRegExp;
        this.breakerRegExp = breakerRegExp;
    }




    @Override
    public List<String> kabelLines() throws IOException {
        return new StringsByRegExpFromText(
                Pattern.compile(lineRegExp),
                new TextFromTxt(fileName).textFromFile()
        ).strings();
    }

    @Override
    public List<String> breakers() throws IOException {
        return new StringsByRegExpFromText(
                Pattern.compile(breakerRegExp),
                new TextFromTxt(fileName).textFromFile()
        ).strings();
    }

    @Override
    public List<String> lines() throws IOException {
        return null;
    }

    @Override
    public List<String> purposes() throws IOException {
        return null;
    }

    @Override
    public String fileName() throws IOException {
        return this.fileName;
    }


}
