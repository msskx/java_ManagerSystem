package com.msskx.java.view;


import com.msskx.java.controller.UpdateViewController;
import com.msskx.java.model.EmployeeDO;
import com.msskx.java.service.EmployeeService;
import com.msskx.java.service.impl.EmployeeServiceImpl;

import javax.swing.*;
import java.awt.*;

public class UpdateView extends JDialog {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel idLabel=new JLabel("工 号：",JLabel.RIGHT);
    JTextField idTxt=new JTextField();
    JLabel nameLabel=new JLabel("姓 名：",JLabel.RIGHT);
    JTextField nameTxt=new JTextField();
    JLabel ageLabel =new JLabel("年 龄：",JLabel.RIGHT);
    JTextField ageTxt =new JTextField();

    JLabel sexLabel=new JLabel("性 别：",JLabel.RIGHT);
    //JTextField sexTxt=new JTextField();
    JComboBox<String>sexTxt=new JComboBox<>();
    JLabel baseLabel =new JLabel("基础工资：",JLabel.RIGHT);
    JTextField baseTxt =new JTextField();

    JLabel bonusLabel =new JLabel("奖 金：",JLabel.RIGHT);
    JTextField bonusTxt =new JTextField();
    JLabel apartmentLabel =new JLabel("部 门：",JLabel.RIGHT);
   // JTextField apartmentTxt =new JTextField();
   JComboBox<String>apartmentTxt=new JComboBox<>();
    JLabel positionLabel =new JLabel("职 位：",JLabel.RIGHT);
    //JTextField positionTxt =new JTextField();
    JComboBox<String>positionTxt=new JComboBox<>();
    JButton updateBtn =new JButton("修改");
    UpdateViewController updateViewController;

    public UpdateView(MainView mainView,int selectedvegetableId) {
        super(mainView,"修改员工",true);
        updateViewController=new UpdateViewController(this,mainView);
        //查询selectedStudentId对应的记录并回显
        EmployeeService employeeService=new EmployeeServiceImpl();
        EmployeeDO employeeDO=employeeService.getById(selectedvegetableId);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        idLabel.setFont(new Font("楷体",Font.PLAIN,15));
        idTxt.setFont(new Font("楷体",Font.PLAIN,15));
        idTxt.setText(employeeDO.getId()+"");
        //设置id不可编辑
        idTxt.setEnabled(false);
        jPanel.add(idTxt);
        nameLabel.setPreferredSize(new Dimension(80,30));
        nameTxt.setPreferredSize(new Dimension(200,30));
        nameLabel.setFont(new Font("楷体",Font.PLAIN,15));
        nameTxt.setFont(new Font("楷体",Font.PLAIN,15));
        nameTxt.setText(employeeDO.getName());
        jPanel.add(nameLabel);
        jPanel.add(nameTxt);
        ageLabel.setPreferredSize(new Dimension(80,30));
        ageTxt.setPreferredSize(new Dimension(200,30));
        ageLabel.setFont(new Font("楷体",Font.PLAIN,15));
        ageTxt.setFont(new Font("楷体",Font.PLAIN,15));
        ageTxt.setText(String.valueOf(employeeDO.getAge()));
        jPanel.add(ageLabel);
        jPanel.add(ageTxt);
        sexLabel.setPreferredSize(new Dimension(80,30));
        sexTxt.setPreferredSize(new Dimension(200,30));
        sexLabel.setFont(new Font("楷体",Font.PLAIN,15));
        sexTxt.setFont(new Font("楷体",Font.PLAIN,15));
        //sexTxt.setText(employeeDO.getSex());//改为下拉框
        sexTxt.addItem("男");
        sexTxt.addItem("女");
        sexTxt.setSelectedItem(employeeDO.getSex());
        jPanel.add(sexLabel);
        jPanel.add(sexTxt);
        baseLabel.setPreferredSize(new Dimension(80,30));
        baseTxt.setPreferredSize(new Dimension(200,30));
        baseLabel.setFont(new Font("楷体",Font.PLAIN,15));
        baseTxt.setFont(new Font("楷体",Font.PLAIN,15));
        baseTxt.setText(String.valueOf(employeeDO.getBaseSalary()));
        jPanel.add(baseLabel);
        jPanel.add(baseTxt);
        bonusLabel.setPreferredSize(new Dimension(80,30));
        bonusTxt.setPreferredSize(new Dimension(200,30));
        bonusLabel.setFont(new Font("楷体",Font.PLAIN,15));
        bonusTxt.setFont(new Font("楷体",Font.PLAIN,15));
        bonusTxt.setText(String.valueOf(employeeDO.getBonus()));

        jPanel.add(bonusLabel);
        jPanel.add(bonusTxt);
        apartmentLabel.setPreferredSize(new Dimension(80,30));
        apartmentTxt.setPreferredSize(new Dimension(200,30));
        apartmentLabel.setFont(new Font("楷体",Font.PLAIN,15));
        apartmentTxt.setFont(new Font("楷体",Font.PLAIN,15));
        apartmentTxt.addItem("研发部");
        apartmentTxt.addItem("运营部");
        apartmentTxt.addItem("产品部");
        apartmentTxt.addItem("市场部");
        apartmentTxt.addItem("销售部");
        //apartmentTxt.setText(employeeDO.getApartment());
        apartmentTxt.setSelectedItem(employeeDO.getApartment());
        jPanel.add(apartmentLabel);
        jPanel.add(apartmentTxt);

        positionLabel.setPreferredSize(new Dimension(80,30));
        positionTxt.setPreferredSize(new Dimension(200,30));
        positionLabel.setFont(new Font("楷体",Font.PLAIN,15));
        positionTxt.setFont(new Font("楷体",Font.PLAIN,15));
        //positionTxt.setText(employeeDO.getPosition());
        positionTxt.addItem("助理");
        positionTxt.addItem("经理");
        positionTxt.addItem("编辑");
        positionTxt.addItem("职员");
        positionTxt.addItem("客服");
        positionTxt.setSelectedItem(employeeDO.getPosition());
        jPanel.add(positionLabel);
        jPanel.add(positionTxt);
        updateBtn.addActionListener(updateViewController);
        updateBtn.setFont(new Font("楷体",Font.PLAIN,15));
        jPanel.add(updateBtn);
        Container container=getContentPane();
        container.add(jPanel);

        setIconImage(new ImageIcon(".\\src\\com\\msskx\\java\\res\\icon.png").getImage());

        setSize(360,500);
        setLocationRelativeTo(null);
        //只销毁当前窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public EmployeeDO buildUpdateEmployeeDO() {
        EmployeeDO employeeDO=new EmployeeDO();
        if(nameTxt.getText().equals("")){
            JOptionPane.showMessageDialog(this,"姓名不可以为空");
            return null;
        }
        if(Integer.valueOf(ageTxt.getText())<0){
            JOptionPane.showMessageDialog(this,"请输入正常年龄");
            return null;
        }
        employeeDO.setId(Integer.valueOf(idTxt.getText()));
        employeeDO.setName(nameTxt.getText());
        employeeDO.setAge(Integer.valueOf(ageTxt.getText()));
        //employeeDO.setSex(sexTxt.getText());//性别修改为下拉选择框
        employeeDO.setSex((String) sexTxt.getSelectedItem());
        employeeDO.setBaseSalary(Double.valueOf(baseTxt.getText()));
        employeeDO.setBonus(Double.valueOf(bonusTxt.getText()));
        employeeDO.setSum(Double.valueOf(baseTxt.getText())+Double.valueOf(bonusTxt.getText()));
        employeeDO.setApartment((String) apartmentTxt.getSelectedItem());
        employeeDO.setPosition((String) positionTxt.getSelectedItem());
        return employeeDO;
    }
}
