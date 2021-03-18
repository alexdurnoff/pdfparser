package dialog;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Возвращает строковое представление пути к выбранному файлу.
 * Вместо него в программе будем использовать более универсальный
 * FileNamesDialog, который возвращает список строковых представлений путей к файлам.
 */
@Deprecated
public class FileNameDialog {
    private final String title;
    private final String buttonText;
    private final String fileType;

    public FileNameDialog(String title, String buttonText, String fileType){
        this.title = title;
        this.buttonText = buttonText;
        this.fileType = fileType;
    }

    public String fileName() {
        String fileName;
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
            fileName = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            fileName = "";
            System.exit(-100);
        }
        return fileName;
    }

}
