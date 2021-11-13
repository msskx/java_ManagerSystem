package main.java.com.roadjava.student.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class LoginView extends JFrame {
    JLabel nameLabel=new JLabel("Msskx学生",JLabel.CENTER);

    SpringLayout springLayout=new SpringLayout();
    JPanel centerPanel=new JPanel(springLayout);
    JLabel userNameLabel=new JLabel("用户名:");
    JTextField userTxt=new JTextField();
    JLabel pwdLabel=new JLabel("密码:");
    JPasswordField pwdField=new JPasswordField();
    JButton loginBtn=new JButton("登录");
    JButton resetBtn=new JButton("重置");

    //托盘
    SystemTray systemTray;
    TrayIcon trayIcon;
    public LoginView(){
        //标题
        super("应用");
        Container container = getContentPane();

        nameLabel.setFont(new Font("华文行楷",Font.PLAIN,40));
        nameLabel.setPreferredSize(new Dimension(0,80));
        Font centerFont=new Font("楷体",Font.PLAIN,20);

        userNameLabel.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(200,30));
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);

        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        centerPanel.add(loginBtn);
        centerPanel.add(resetBtn);


        //弹簧布局
        //userNameLabel
        //取出center两个控件在一行时的总宽度=(userNameLabel+userTxt+二者间距20)
        Spring childWidth=Spring.sum(Spring.sum(Spring.width(userNameLabel),Spring.width(userTxt)),
                Spring.constant(20));

        //正中心
        int offsetX=childWidth.getValue()/2;
        //从左往右，左偏移
        //标签的西边相对于面板中心向左对齐
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        //标签的北边相对于面板北边对齐间距20
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,
                SpringLayout.NORTH,centerPanel);
        //userTxt
        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH,userNameLabel);

        //pwdLabel
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH,userNameLabel);
        //pwdField
        springLayout.putConstraint(SpringLayout.WEST,pwdField,20,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLabel);
        //loginbtn
        springLayout.putConstraint(SpringLayout.EAST,loginBtn,50,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,20,SpringLayout.SOUTH,pwdLabel);
        //resetbtn
        springLayout.putConstraint(SpringLayout.EAST,resetBtn,-50,SpringLayout.EAST,pwdField);
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,20,SpringLayout.SOUTH,pwdLabel);



        container.add(nameLabel,BorderLayout.NORTH);
        container.add(centerPanel,BorderLayout.CENTER);

        //添加系统托盘(暂时存在显示不全的问题)
        if(SystemTray.isSupported()){
            systemTray=SystemTray.getSystemTray();
            trayIcon=new TrayIcon(new ImageIcon(".\\src\\main\\res\\sun.png").getImage());
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            //增加最小化时销毁资源
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    LoginView.this.dispose();//匿名内部类使用此方法访问外部
                }
            });
            //托盘事件监听
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount()==1){
                        LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
                    //当鼠标点击托盘托盘重新显示
                    LoginView.this.setVisible(true);
                }
            });
        }

        //设置图标失败
        setIconImage(new ImageIcon(".\\src\\main\\res\\icon.png").getImage());
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
