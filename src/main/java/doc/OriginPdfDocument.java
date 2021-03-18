package doc;

import dialog.FileNameDialog;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class OriginPdfDocument implements FileName{
    private final String fileName;


    public OriginPdfDocument() {
        this.fileName = new FileNameDialog(
                "Выбор исходного pdf-файла",
                "Выбрать файл",
                "pdf"
        ).fileName();
    }


    @Override
    public String fileName() {
        return this.fileName;
    }
}
