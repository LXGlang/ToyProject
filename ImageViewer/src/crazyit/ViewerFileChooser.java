package crazyit;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ViewerFileChooser extends JFileChooser {
    public ViewerFileChooser() {
        super();
        setAcceptAllFileFilterUsed(false);
        addFilter();
    }

    private void addFilter() {
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP" },
                "BMP (*.BMP)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".JPG",
                ".JPEG", ".JPE", ".JFIF" },
                "JPEG (*.JPG;*.JPEG;*.JPE;*.JFIF)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".GIF" },
                "GIF (*.GIF)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".TIF",
                ".TIFF" }, "TIFF (*.TIF;*.TIFF)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".PNG" },
                "PNG (*.PNG)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".ICO" },
                "ICO (*.ICO)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP",
                ".JPG", ".JPEG", ".JPE", ".JFIF", ".GIF", ".TIF", ".TIFF",
                ".PNG", ".ICO" }, "所有图形文件"));
    }

    class MyFileFilter extends FileFilter {
        String[] suffArr;
        String discription;

        public MyFileFilter() {
            super();
        }

        public MyFileFilter(String[] suffArr, String discription) {
            super();
            this.suffArr = suffArr;
            this.discription = discription;
        }

        @Override
        public boolean accept(File f) {
            for (String s : suffArr) {
                if (f.getName().toUpperCase().endsWith(s)) {
                    return true;
                }
            }
            return f.isDirectory();
        }

        @Override
        public String getDescription() {
            return this.discription;
        }
    }
}
