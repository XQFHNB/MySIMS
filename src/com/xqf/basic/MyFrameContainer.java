package com.xqf.basic;

import javax.swing.*;
import java.util.Hashtable;

/**
 * Created by XQF on 2016/12/5.
 */
public class MyFrameContainer {
    private static Hashtable<String, JFrame> table = new Hashtable<>();

    public static JFrame getFrame(String name) {
        return table.get(name);
    }

    public static void putFrame(String name, JFrame frame) {
        table.put(name, frame);
    }
}
