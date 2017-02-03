package crazyit;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewerService {
    public static ViewerService service = null;
    ViewerFileChooser fileChooser = new ViewerFileChooser();
    private double range = 0.2;
    private File currentDirectory = null;
    private List<File> currentFiles = null;
    private File currentFile = null;
    public ViewerService() { }

    /**
     * 获取单态实例
     * @return
     */
    public static ViewerService getInstance() {
        if (null == service) {
            service = new ViewerService();
        }
        return service;
    }

    public void menuDo(ViewerFrame frame, String cmd) {
        switch (cmd) {
            case "打开(O)":
                open(frame);
                break;
            case "放大(M)":
                zoom(frame, true);
                break;
            case "缩小(O)":
                zoom(frame, false);
                break;
            case "上一个(X)":
                last(frame);
                break;
            case "下一个(P)":
                next(frame);
                break;
            case "退出(X)":
                System.exit(0);
                break;
        }
    }

    public void open(ViewerFrame frame) {
        if (ViewerFileChooser.APPROVE_OPTION ==
                fileChooser.showOpenDialog(frame)) {
            this.currentFile = fileChooser.getSelectedFile();
            String name = this.currentFile.getPath();
            File cd = fileChooser.getCurrentDirectory();
            if (cd != this.currentDirectory
                    || this.currentDirectory == null) {
                FileFilter[] fileFilters = fileChooser.
                        getChoosableFileFilters();
                File[] files = cd.listFiles();
                this.currentFiles = new ArrayList<>();
                for (File file : files) {
                    for (FileFilter filter : fileFilters) {
                        if (filter.accept(file)) {
                            this.currentFiles.add(file);
                        }
                    }
                }
            }
            ImageIcon icon = new ImageIcon(name);
            frame.getLabel().setIcon(icon);
        }
    }

    public void zoom(ViewerFrame frame, boolean isEnlarge) {
        double enLargeRange = isEnlarge ? 1 + range : 1 -  range;
        ImageIcon icon = (ImageIcon) frame.getLabel().getIcon();
        if (icon != null) {
            int width = (int) (icon.getIconWidth()*enLargeRange);
            ImageIcon newIcon = new ImageIcon(icon.getImage().
                    getScaledInstance(width, -1, Image.SCALE_DEFAULT));
            frame.getLabel().setIcon(newIcon);
        }
    }

    public void last(ViewerFrame frame) {
        if (this.currentFiles != null && !this.currentFiles.isEmpty()) {
            int index = this.currentFiles.indexOf(this.currentFile);
            if (index > 0) {
                File file = this.currentFiles.get(index-1);
                ImageIcon icon = new ImageIcon(file.getPath());
                frame.getLabel().setIcon(icon);
                this.currentFile = file;
            }
        }
    }

    public void next(ViewerFrame frame) {
        if (this.currentFiles != null
            && !this.currentFiles.isEmpty()) {
            // ??????????????????????????????????
            int index = this.currentFiles.indexOf(this.currentFile) + 1;
            if (index < this.currentFiles.size() - 1) {
                File file = this.currentFiles.get(index+1);
                ImageIcon icon = new ImageIcon(file.getPath());
                frame.getLabel().setIcon(icon);
                this.currentFile = file;
            }
        }
    }
}
