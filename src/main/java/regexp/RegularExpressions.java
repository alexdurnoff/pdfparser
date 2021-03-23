package regexp;

import java.io.IOException;

/**
 * Имплементация интерфейса должна возвращать
 * регулярные выражения для поиска строк
 * в тексте.
 */
public interface RegularExpressions {
    String kabelRegExp() throws IOException;
    String breakerRegExp() throws IOException;
    String linesRegExp() throws IOException;
    String purposeRegExp() throws IOException;
}
