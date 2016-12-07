package com.xqf.basic;

import javax.swing.*;

/**
 * Created by XQF on 2016/12/5.
 */
public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Crystal");
        frame.setLayout(null);
        JLabel title = new JLabel("bbs.beyole.com");
        JLabel enter = new JLabel("进入");
        JLabel exit = new JLabel("退出");

        frame.setSize(200, 100);
        title.setBounds(40, 5, 150, 20);
        enter.setBounds(30, 30, 80, 20);
        exit.setBounds(110, 30, 80, 20);

        frame.add(title);
        frame.add(enter);
        frame.add(exit);
        frame.setVisible(true);
    }
}