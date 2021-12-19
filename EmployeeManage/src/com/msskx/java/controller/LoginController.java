package com.msskx.java.controller;

import com.msskx.java.model.AdminDO;
import com.msskx.java.service.AdminService;
import com.msskx.java.service.impl.AdminServiceImpl;

import com.msskx.java.view.EmployeeMainView;
import com.msskx.java.view.LoginView;
import com.msskx.java.view.MainView;
import com.msskx.java.view.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginController extends KeyAdapter implements ActionListener {
    //继承KeyAdapter适配器避免了过多无用方法出现，实现ActionListener接口添加监听事件
    private LoginView loginView;
    public LoginController(LoginView loginView) {
        this.loginView=loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton= (JButton) e.getSource();
        String text=jButton.getText();
        if("注册".equals(text)){
            //register();
            new RegisterView(loginView);

        }else if("管理登录".equals(text)){
            login();
           // new EmployeeMainView();
            //System.out.println("登录成功");
        }else if("用户登录".equals(text)){
            employeelogin();

        }
    }
    private void login() {
        String user= loginView.getUserField().getText();
        char[]chars=loginView.getPasswordField().getPassword();
        if(user==null||"".equals(user.trim())||chars==null||chars.length==0){
            JOptionPane.showMessageDialog(loginView,"用户名密码必填");
            return;
        }
        String passWord=new String(chars);
        //查询数据库
        AdminService adminService=new AdminServiceImpl();//创建校验对象
        AdminDO adminDO=new AdminDO();//创建管理账户对象
        //将键盘上接受的账号密码赋值给账户对象
        adminDO.setUserName(user);
        adminDO.setPwd(passWord);

        //登录成功的标志
        boolean flag= adminService.validata(adminDO);
        if (flag){
            loginView.dispose();
            new MainView();
            System.out.println("登录成功");
        }else{
            JOptionPane.showMessageDialog(loginView,"用户名或密码错误");
        }
    }
    private void employeelogin() {
        String user= loginView.getUserField().getText();
        char[]chars=loginView.getPasswordField().getPassword();
        if(user==null||"".equals(user.trim())||chars==null||chars.length==0){
            JOptionPane.showMessageDialog(loginView,"用户名密码必填");
            return;
        }
        String passWord=new String(chars);
        //查询数据库
        AdminService adminService=new AdminServiceImpl();//创建校验对象
        AdminDO adminDO=new AdminDO();//创建管理账户对象
        //将键盘上接受的账号密码赋值给账户对象
        adminDO.setUserName(user);
        adminDO.setPwd(passWord);

        //登录成功的标志
        boolean flag= adminService.employeevalidata(adminDO);
        if (flag){
            loginView.dispose();
            new EmployeeMainView();
            System.out.println("登录成功");
        }else{
            JOptionPane.showMessageDialog(loginView,"用户名或密码错误");
        }
    }
//    private void register(){
//        String user= loginView.getUserField().getText();
//
//        char[]chars=loginView.getPasswordField().getPassword();
//        if(user==null||"".equals(user.trim())||chars==null||chars.length==0){
//            JOptionPane.showMessageDialog(loginView,"用户名或密码为空");
//            return;
//        }
//        String passWord=new String(chars);
//        //查询数据库
//        AdminService adminService=new AdminServiceImpl();//创建校验对象
//        AdminDO adminDO=new AdminDO();//创建管理账户对象
//        //将键盘上接受的账号密码赋值给账户对象
//        adminDO.setUserName(user);
//        adminDO.setPwd(passWord);
//        //账户存在
//        boolean flag= adminService.existdata(adminDO);
//        if (flag){
//            JOptionPane.showMessageDialog(loginView,"该账户已存在");
//        }else{
//            //注册，在数据库中插入信息
//            boolean registerFlag = adminService.registerdata(adminDO);
//            if(registerFlag){
//                JOptionPane.showMessageDialog(loginView,"注册成功");
//            }
//        }
//    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER==e.getKeyCode()){
            login();//如果回车就登录
        }
    }
}
