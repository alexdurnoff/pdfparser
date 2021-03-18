package doc;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class OriginPdfDocument implements FileName{
    private final String fileName;


    public OriginPdfDocument() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выбор исходного pdf-файла");
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) return true;
                return file.getName().contains(".pdf");
            }

            @Override
            public String getDescription() {
                return "pdf";
            }
        });
        fileChooser.setSize(800,600);
        fileChooser.setCurrentDirectory(new File("/home/alexej/Документы/Работа"));
        fileChooser.setDialogTitle("Выберите исходный pdf-файл");
        int res = fileChooser.showDialog(null, "Выбрать файл");
        if (res == JFileChooser.APPROVE_OPTION){
            this.fileName = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            this.fileName = "";
            System.exit(-100);
        }
    }


    @Override
    public String fileName() {
        return this.fileName;
    }
}
