package crazyit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewerFrame extends JFrame {
    private JLabel label = new JLabel();
    ViewerService service = ViewerService.getInstance();

    ActionListener menuListener = e -> {
        service.menuDo(ViewerFrame.this, e.getActionCommand());
    };

    public ViewerFrame() {
        super();
        init();
    }

    private void init() {
        this.setTitle("JavaSwing ImageViewer");

        // set program size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setPreferredSize(dimension);

        createMenuBar();

        JPanel toolBar = createToolPanel();
        this.add(toolBar, BorderLayout.NORTH);
        this.add(new JScrollPane(label), BorderLayout.CENTER);

        this.setVisible(true);
        this.pack();
    }


    private JPanel createToolPanel() {
        JPanel panel = new JPanel();
        JToolBar toolBar = new JToolBar("工具");
        toolBar.setFloatable(false);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        String[] toolArr = {"OpenAction", "LastAction", "NextAction",
                                            "BigAction", "SmallAction"};
        for (int i = 0; i < toolArr.length; i++) {
            ViewerAction action = new ViewerAction(new ImageIcon("img/" +
                    toolArr[i] + ".gif"), toolArr[i], this);
            JButton button = new JButton(action);
            toolBar.add(button);
        }
        panel.add(toolBar);
        return panel;
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        String[] menuArr = {"文件(F)", "工具(T)", "帮助(H)"};

        String[][] menuItemArr = {{"打开(O)", "-", "退出(X)"},
                {"放大(M)", "缩小(O)", "-", "上一个(X)", "下一个(P)"},
                {"帮助主题", "关于"}};

        // create menu
        for (int i = 0; i < menuArr.length; i++) {
            JMenu menu = new JMenu(menuArr[i]);
            for (int j = 0; j < menuItemArr[i].length; j++) {
                if (menuItemArr[i][j].equals("-")) {
                    menu.addSeparator();
                } else {
                    JMenuItem menuItem = new JMenuItem(menuItemArr[i][j]);
                    menuItem.addActionListener(menuListener);
                    menu.add(menuItem);
                }
            }
            menuBar.add(menu);
        }
        this.setJMenuBar(menuBar);
    }

    public JLabel getLabel() {
        return this.label;
    }
}
