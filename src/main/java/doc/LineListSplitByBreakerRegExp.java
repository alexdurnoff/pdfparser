package doc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс инкапсулирует строку - текст из страницы проекта и
 * регулярное выражение для поиска автоматов в тексте.
 * Фактически, перед каждым совпадением с шаблоном метод
 * вставляет перенос строки.
 * Класс возвращает список строк, разделенных по началу регулярного
 * выражения.
 */
public class LineListSplitByBreakerRegExp {
    private final String text;
    private final String breakerRegExp;

    public LineListSplitByBreakerRegExp(String text, String breakerRegExp){
        this.text = text;
        this.breakerRegExp = breakerRegExp;
    }

    public List<String> linesSplitByRegExp(){
        String result = text;
        List<String> checkedStrings = new ArrayList<>();
        Pattern pattern = Pattern.compile(breakerRegExp);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            String replacedString  = matcher.group(0);
            if (!checkedStrings.contains(replacedString)){
                result = result.replace(replacedString, "\n" + replacedString);
                checkedStrings.add(replacedString);
            }
        }
        return Arrays.asList(result.split("\n").clone());
    }
}
