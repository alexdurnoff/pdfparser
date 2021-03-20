package doc.stringpostprocessors;

/**
 * Пустой StringPostProccessor. Используем тогда, когда
 * дополнительная обработка текста не нужна.
 */
public class DefaultStringPostProcessor implements StringPostProcessor{
    @Override
    public String postProcess(String text) {
        return text;
    }
}
