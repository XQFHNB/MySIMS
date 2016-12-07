package com.xqf.basic;

import javax.swing.*;

/**
 * Created by XQF on 2016/12/5.
 */
public class MyFrame extends JFrame {
    public MyFrame(String titleString){
        this.setTitle(titleString);
        this.setVisible(true);
        this.setSize(600,450);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }



    public static void main(String[] args) {
        // 实例化对象
        MyFrame frame=new MyFrame("heh");
    }

}