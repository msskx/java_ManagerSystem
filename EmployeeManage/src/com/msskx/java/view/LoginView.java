package com.msskx.java.view;



import com.msskx.java.controller.LoginController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JFrame {
    /**
     * 登录标题，用户名，密码，用户名输入框，密码输入框
     * 登录按钮，注册按钮
     */
    JLabel titleLabel=new JLabel("员工工资管理系统",JLabel.CENTER);
    JLabel userLabel=new JLabel("用户名:",JLabel.CENTER);
    JLabel passwordLabel=new JLabel("密 码:",JLabel.CENTER);
    JTextField userField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton loginButton=new JButton("管理登录");
    JButton employeeLoginButton=new JButton("用户登录");
    JButton registerButton=new JButton("注册");
    //定义弹簧布局
    SpringLayout springLayout=new SpringLayout();
    //定义根面板并设置为弹簧布局
    JPanel centerPanel =new JPanel(springLayout);
    //监听
    LoginController loginController;

    //初始化
    public LoginView(){
        super("员工工资管理系统");
        Container container=getContentPane();
//设置每个控件的字体和大小
        titleLabel.setFont(new Font("幼圆",Font.BOLD,40));
        titleLabel.setSize(new Dimension(0,80));

        userLabel.setFont(new Font("楷体",Font.PLAIN,20));
        userField.setPreferredSize(new Dimension(200,30));
        passwordLabel.setFont(new Font("楷体",Font.PLAIN,20));
        passwordField.setPreferredSize(new Dimension(200,30));
        loginButton.setFont(new Font("楷体",Font.PLAIN,20));
        employeeLoginButton.setFont(new Font("楷体",Font.PLAIN,20));
        registerButton.setFont(new Font("楷体",Font.PLAIN,20));

        //添加登录监听
        loginController =new LoginController(this);
        centerPanel.add(userLabel);
        centerPanel.add(userField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        loginButton.addActionListener(loginController);//鼠标点击登录
        loginButton.addKeyListener(loginController);//按键事件，回车确认登录
        centerPanel.add(loginButton);
        registerButton.addActionListener(loginController);//鼠标点击注册
        centerPanel.add(employeeLoginButton);//用户登录
        employeeLoginButton.addActionListener(loginController);
        centerPanel.add(registerButton);
        //对控件进行弹簧布局
        //取出两个控件在一行的总宽度和之间预留的间距
        Spring childWidth=Spring.sum(Spring.sum(Spring.width(userLabel),Spring.width(userField)),Spring.constant(30));
        int offsetX=childWidth.getValue()/2;//正中心
        //前面从属物后面是锚点
        //userLabel的西边相对于面板的中心向左
        //userLabel的北边相对于面板的北边距离pad
        springLayout.putConstraint(SpringLayout.WEST,userLabel,-offsetX,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userLabel,100,SpringLayout.NORTH,centerPanel);
        //以userlabel为锚点对userField进行布局
        //userField北边和userLabel一样,西边靠中心offset
        springLayout.putConstraint(SpringLayout.EAST,userField,offsetX,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userField,0,SpringLayout.NORTH,userLabel);
        //passwordLabel的西边和userLabel相同，北边还有距离
        springLayout.putConstraint(SpringLayout.WEST,passwordLabel,0,SpringLayout.WEST,userLabel);
        springLayout.putConstraint(SpringLayout.NORTH,passwordLabel,30,SpringLayout.SOUTH,userField);
        //passwordField的东边和userLabel相同，北边还有距离
        springLayout.putConstraint(SpringLayout.EAST,passwordField,0,SpringLayout.EAST,userField);
        springLayout.putConstraint(SpringLayout.NORTH,passwordField,30,SpringLayout.SOUTH,userField);

        //LoginButton的西边和passwordLabel有距离，北边还有距离
        springLayout.putConstraint(SpringLayout.WEST,loginButton,-20,SpringLayout.WEST,passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginButton,40,SpringLayout.SOUTH,passwordLabel);
        //用户登录的西边和passwordLabel有距离，北边还有距离
        springLayout.putConstraint(SpringLayout.WEST,employeeLoginButton,132,SpringLayout.WEST,loginButton);
        springLayout.putConstraint(SpringLayout.NORTH,employeeLoginButton,40,SpringLayout.SOUTH,passwordLabel);
        //registerButton的东边和passwordField有距离，北边还有距离
        springLayout.putConstraint(SpringLayout.EAST,registerButton,20,SpringLayout.EAST,passwordField);
        springLayout.putConstraint(SpringLayout.NORTH,registerButton,40,SpringLayout.SOUTH,passwordLabel);
        //将标题向下移动
        titleLabel.setBounds(new Rectangle());
//设置标题标签与JFrame的距离
        titleLabel.setBorder(new EmptyBorder(20, 10, 10, 10));
        container.add(titleLabel,BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);
/**
 * 图片图标存在显示问题
 * 添加托盘存在图标无法显示问题
 */
//        //添加托盘
//        if(SystemTray.isSupported()){
//            SystemTray systemTray = SystemTray.getSystemTray();
//            TrayIcon trayIcon = new TrayIcon(new ImageIcon("./src/res/iii.jpg").getImage());
//            try {
//                systemTray.add(trayIcon);
//            } catch (AWTException e) {
//                e.printStackTrace();
//            }
//            //最小化时销毁资源
//            this.addWindowListener((new WindowAdapter() {
//                @Override
//                public void windowIconified(WindowEvent e) {
//                    LoginView.this.dispose();
//                }
//            }));
//            //托盘事件监听
//            trayIcon.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    if(e.getClickCount()==1){
//                        LoginView.this.setExtendedState(JFrame.NORMAL);
//                    }
//                    //鼠标点击托盘，托盘重新显示
//                    LoginView.this.setVisible(true);
//                }
//            });
//        }

        getRootPane().setDefaultButton(loginButton);

        setIconImage(new ImageIcon(".\\src\\com\\msskx\\java\\res\\icon.png").getImage());

        setSize(800,500);
        setLocationRelativeTo(null);//定位与其他无关默认居中避免计算
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    //返回输入框的内容
    public JTextField getUserField() {
        return userField;
    }
    public JPasswordField getPasswordField() {
        return passwordField;
    }
}
