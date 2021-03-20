package doc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс инкапасулирует строку и регулярное выражение для кабелей.
 * Возвращает в единственном методе
 * список строк, полученных посредством разделения исходной строки
 * путем добавления переноса строки после окончания регулярного выражения.
 */
public class LineListWithFullInformation {
    private final String string;
    private final String kabelRegExp;

    public LineListWithFullInformation(String string, String kabelRegExp){
        this.string = string;
        this.kabelRegExp = kabelRegExp;
    }

    public List<String> linesWithFullInformation(){
        String result = string;
        List<String> checkedStrings = new ArrayList<>();
        Pattern pattern = Pattern.compile(kabelRegExp);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()){
            String replacedString  = matcher.group(0);
            if (!checkedStrings.contains(replacedString)){
                result = result.replace(replacedString, replacedString + "\n");
                checkedStrings.add(replacedString);
            }
        }
        return Arrays.asList(result.split("\n"));
    }
}
