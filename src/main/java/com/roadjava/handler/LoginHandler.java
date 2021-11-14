package com.roadjava.handler;

import com.roadjava.entity.AdminDO;
import com.roadjava.service.AdminService;
import com.roadjava.service.impl.AdminServiceImpl;
import com.roadjava.student.view.LoginView;
import com.roadjava.student.view.MainView;

import javax.swing.*;
import java.awt.event.*;

public class LoginHandler extends KeyAdapter implements ActionListener {

    private LoginView loginView;
    public LoginHandler(LoginView loginView) {
        this.loginView=loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton)e.getSource();
        String text= jButton.getText();
        if("重置".equals(text)){
//            System.out.println("reset");
            loginView.getUserTxt().setText("");
            loginView.getPwdField().setText("");
        }else if("登录".equals(text)){
//            System.out.println("登录");
            login();
        }
    }

    private void login() {
        String user=loginView.getUserTxt().getText();
        char[]chars=loginView.getPwdField().getPassword();
        if(user==null||"".equals(user.trim())||chars==null){
            JOptionPane.showMessageDialog(loginView,"用户名密码必填");
            return;
        }
        String pwd =new String(chars);
        System.out.println(user + ":" + pwd);
        //查询database
        AdminService adminService=new AdminServiceImpl();
        AdminDO adminDO=new AdminDO();
        adminDO.setUserName(user);
        adminDO.setPwd(pwd);


        boolean flag=adminService.validateAdmin(adminDO);
        if(flag){
            new MainView();
            loginView.dispose();
        }else{
            //密码输入错误弹出模态对话框
            JOptionPane.showMessageDialog(loginView,"用户名密码错误");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER==e.getKeyCode()){
            login();
        }
    }
}
