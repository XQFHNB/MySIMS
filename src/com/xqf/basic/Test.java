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


        JButton button = new JButton("cndk");
        button.setBounds(300, 200, 150, 20);
//        enter.setBounds(30, 30, 80, 20);
//        exit.setBounds(110, 30, 80, 20);

        frame.add(button);
//        frame.add(enter);
//        frame.add(exit);
        frame.setVisible(true);
        frame.setSize(600, 450);
    }
}