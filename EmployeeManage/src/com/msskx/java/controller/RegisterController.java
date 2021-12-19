package com.msskx.java.controller;


import com.msskx.java.model.AdminDO;
import com.msskx.java.service.AdminService;
import com.msskx.java.service.impl.AdminServiceImpl;
import com.msskx.java.view.LoginView;
import com.msskx.java.view.RegisterView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterController implements ActionListener {
    //继承KeyAdapter适配器避免了过多无用方法出现，实现ActionListener接口添加监听事件
    private LoginView loginView;
    private RegisterView registerView;

    public RegisterController(LoginView loginView, RegisterView registerView) {
        this.loginView = loginView;
        this.registerView = registerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton= (JButton) e.getSource();
        String text=jButton.getText();
        if("注册".equals(text)){
            register();
        }
    }
    private void register(){

        AdminDO adminDO=registerView.buildAdminDO();
        String user= adminDO.getUserName();
        String password=adminDO.getPwd();
        if(user==null||"".equals(user.trim())||password==null||"".equals(password.trim())){
            JOptionPane.showMessageDialog(loginView,"用户名或密码为空");
            return;
        }
        //查询数据库
        AdminService adminService=new AdminServiceImpl();//创建校验对象
        //账户存在
        boolean flag= adminService.existdata(adminDO);
        if (flag){
            JOptionPane.showMessageDialog(loginView,"该账户已存在");
        }else{
            //注册，在数据库中插入信息
            boolean registerFlag = adminService.registerdata(adminDO);
            if(registerFlag){
                JOptionPane.showMessageDialog(loginView,"注册成功");
                registerView.dispose();
            }
        }
    }

}
