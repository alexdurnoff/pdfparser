package doc;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс представляет собой набор одностраничных
 * pdf-документов.
 */
public interface Pages {
    /**
     * Возвращает список одностраничных pdf-документов.
     * Очень важно, чтобы количество страниц было четко равно 1.
     * @return List<String>.
     */
    List<String> onePagePdfFiles() throws IOException;
}
