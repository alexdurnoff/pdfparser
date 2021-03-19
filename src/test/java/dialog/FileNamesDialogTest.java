package dialog;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileNamesDialogTest {

    @Test
    void fileNames() {
        List<String> fileNames = new FileNamesDialog(
                "Тест диалога выбоора файлов",
                "Выбрать файлы",
                "pdf"

        ).fileNames();
        System.out.println(fileNames.size());
    }
}