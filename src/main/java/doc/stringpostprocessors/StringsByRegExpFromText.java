package doc.stringpostprocessors;

import doc.ListStringFromText;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringsByRegExpFromText implements ListStringFromText {
    private final Pattern pattern;
    private final String text;

    public StringsByRegExpFromText(Pattern pattern, String text){
        this.pattern = pattern;
        this.text = text;
    }


    @Override
    public List<String> strings() {
        List<String> stringList = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            stringList.add(matcher.group(0));
        }
        return stringList;
    }
}
