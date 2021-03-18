package dialog;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Этот класс в отличие от класса
 * FileNameDialog будет возвращать список путей к файлам
 * вместо одной строки. Этот вариант более предпочтительный,
 * так как один файл тоже можно выбрать.
 */
public class FileNamesDialog {
    private final String title;
    private final String buttonText;
    private final String fileType;

    public FileNamesDialog(String title, String buttonText, String fileType){
        this.title = title;
        this.buttonText = buttonText;
        this.fileType = fileType;
    }

    public List<String> fileNames(){
        List<String> fileNames = new ArrayList<>();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSize(800,600);
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) return true;
                return file.getName().contains("." + fileType);
            }

            @Override
            public String getDescription() {
                return fileType;
            }
        });
        fileChooser.setDialogTitle(title);
        int res = fileChooser.showDialog(null, buttonText);
        if (res == JFileChooser.APPROVE_OPTION){
            File[] selectedFiles = fileChooser.getSelectedFiles();
            for (File file : selectedFiles){
                fileNames.add(file.getAbsolutePath());
            }
        } else {
            System.exit(-100);
        }
        return fileNames;
    }
}
