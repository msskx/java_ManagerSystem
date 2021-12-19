package com.msskx.java.view;




import com.msskx.java.controller.RegisterController;
import com.msskx.java.model.AdminDO;


import javax.swing.*;
import java.awt.*;

public class RegisterView extends JDialog {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel nameLabel=new JLabel("用户名：",JLabel.RIGHT);
    JTextField nameTxt=new JTextField();
    JLabel passwordLabel=new JLabel("密 码：",JLabel.RIGHT);
    JPasswordField passwordTxt=new JPasswordField();

    JButton register =new JButton("注册");
    RegisterController registerController;

    public RegisterView(LoginView loginView) {
        super(loginView,"用户注册",true);
        nameLabel.setPreferredSize(new Dimension(80,30));
        nameTxt.setPreferredSize(new Dimension(200,30));
        nameLabel.setFont(new Font("楷体",Font.PLAIN,15));
        jPanel.add(nameLabel);
        jPanel.add(nameTxt);
        passwordLabel.setPreferredSize(new Dimension(80,30));
        passwordLabel.setFont(new Font("楷体",Font.PLAIN,15));
        passwordTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(passwordLabel);
        jPanel.add(passwordTxt);
//        confirmLabel.setPreferredSize(new Dimension(80,30));
//        confirmTxt.setPreferredSize(new Dimension(200,30));
//        jPanel.add(confirmLabel);
//        jPanel.add(confirmTxt);
        registerController=new RegisterController(loginView,this);
        register.addActionListener(registerController);
        register.setFont(new Font("楷体",Font.PLAIN,15));
        jPanel.add(register);
        Container container=getContentPane();
        container.add(jPanel);
        setIconImage(new ImageIcon(".\\src\\com\\msskx\\res\\icon.png").getImage());

        setSize(360,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    //注册得到一个用户对象
    public AdminDO buildAdminDO(){
        AdminDO adminDO=new AdminDO();
        adminDO.setUserName(nameTxt.getText());
        adminDO.setPwd(String.valueOf(passwordTxt.getPassword()));

        return adminDO;
    }
}
