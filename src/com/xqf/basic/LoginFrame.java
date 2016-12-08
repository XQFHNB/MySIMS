package com.xqf.basic;

import com.xqf.config.Config;
import com.xqf.manager.Super;
import com.xqf.student.StudentFrame;
import com.xqf.teacher.TeacherFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by XQF on 2016/12/5.
 */
public class LoginFrame extends JFrame implements ActionListener {

    public static final String SPUER_ITEM = "超级";
    public static final String STUDENT_ITEM = "学生";
    public static final String TEACHER_ITEM = "教师";


    // 用户名
    private JTextField username;
    // 密码
    private JPasswordField password;
    // 小容器
    private JLabel jl1;
    private JLabel jl2;

    // 小按钮
    private JButton bu1;

    // 复选框

    // 列表框
    private JComboBox jcb;

    /*
     * 构造方法
     */
    public LoginFrame() {
        // 设置窗口标题
        this.setTitle("王者数据");
        // 窗体组件初始化
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局方式为绝对定位
        this.setLayout(null);

        this.setBounds(0, 0, 355, 265);
        // 设置窗体的标题图标
//        Image image = new ImageIcon("e:/a.gif").getImage();
//        this.setIconImage(image);

        // 窗体大小不能改变
        this.setResizable(false);

        // 居中显示
        this.setLocationRelativeTo(null);

        // 窗体可见
        this.setVisible(true);
    }

    /*
     * 初始化方法
     */
    public void init() {
        // 创建一个容器
        Container con = this.getContentPane();
        jl1 = new JLabel();
        jl1.setBounds(0, 0, 355, 265);

        jl2 = new JLabel();
        jl2.setBounds(40, 95, 50, 60);

        username = new JTextField();
        username.setBounds(100, 100, 150, 20);

        // 密码输入框
        password = new JPasswordField();
        password.setBounds(100, 130, 150, 20);
        // 密码输入框旁边的文字

        // 用户登录状态选择
        jcb = new JComboBox();
        jcb.addItem(SPUER_ITEM);
        jcb.addItem(TEACHER_ITEM);
        jcb.addItem(STUDENT_ITEM);
        jcb.setBounds(100, 160, 70, 20);

        // 按钮设定
        bu1 = new JButton("登录");
        bu1.setBounds(180, 160, 70, 20);
        bu1.addActionListener(this);

        // 所有组件用容器装载
        jl1.add(jl2);
        jl1.add(jcb);
        jl1.add(bu1);
        con.add(jl1);
        con.add(username);
        con.add(password);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
//        new MyFrame(jcb.getSelectedItem().toString());
//        new Super(jcb.getSelectedItem().toString());
        String itemString = jcb.getSelectedItem().toString();
        if (itemString.equals(LoginFrame.SPUER_ITEM)) {
            // TODO: 2016/12/7 超级管理登陆
            new Super(Config.TITLE);
        }
        if (itemString.equals(LoginFrame.STUDENT_ITEM)) {
            // TODO: 2016/12/7 学生登陆
            String studentNo = username.getText().trim();
            new StudentFrame(Config.TITLE, studentNo);
        }
        if (itemString.equals(TEACHER_ITEM)) {
            String teacherNo = username.getText().trim();
            new TeacherFrame(Config.TITLE, teacherNo);
            // TODO: 2016/12/7 教师登陆
        }
    }

    public static void main(String[] args) {
        // 实例化对象
        LoginFrame loginFrame = new LoginFrame();
    }
}