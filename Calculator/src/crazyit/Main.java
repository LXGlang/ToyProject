/*
*  2016-1-22
*  《疯狂Java实战演义》
*  note: % 有bug.此外把计算器的功能做的太分散，太笨拙；
*  如果拓展应该添加()等支持(参考windows计算器)，
*  直接读入字符串解析（编译原理），
*  而不是分成第一个操作数、第二个操作数、操作符。
*/
package crazyit;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        CalFrame f = new CalFrame();
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
