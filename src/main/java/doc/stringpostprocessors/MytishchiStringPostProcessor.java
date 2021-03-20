package doc.stringpostprocessors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringPostProcessor для объекта в Мытищах.
 * Удаляет пускатели из строки.
 * Инкапсулирует исходный текст.
 * Возвращает текст без пускателей.
 */
public class MytishchiStringPostProcessor implements StringPostProcessor{
    private final static String regExp = "-[63]{2}-[13]-[0-9]{2}\\s-[13]-[0-9]{1,3}[\\s]{2}-[63]{2}-[13]-[0-9]{1,3}";
    private final static Logger logger = LogManager.getLogger(MytishchiStringPostProcessor.class);

    @Override
    public String postProcess(String text) {
        String result = text;
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(result);
        while (matcher.find()){
            String replacementString = matcher.group(0);
            result = result.replace(replacementString, replacementString.substring(0,14));
            matcher= pattern.matcher(result);
        }

        return result;
    }
}
