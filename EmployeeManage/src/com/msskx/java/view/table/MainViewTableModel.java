package com.msskx.java.view.table;


import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableModel extends DefaultTableModel {
    static Vector<String>columns=new Vector<>();

    public static Vector<String> getColumns() {
        return columns;
    }
    static {
        columns.addElement("工号");
        columns.addElement("姓名");
        columns.addElement("年龄");
        columns.addElement("性别");
        columns.addElement("基础工资");
        columns.addElement("奖金");
        columns.addElement("总工资");
        columns.addElement("部门");
        columns.addElement("职位");

    }
    //因为只有表格套用一套model，所以使用单例设计模式
    //构造器私有
    private MainViewTableModel(){
        super(null,columns);
    }
    private static MainViewTableModel mainViewTableModel=new MainViewTableModel();
    public static MainViewTableModel assembleModel(Vector<Vector<Object>>data){
        mainViewTableModel.setDataVector(data,columns);
        return mainViewTableModel;
    }
    public static void updateModel(Vector<Vector<Object>>data){
        mainViewTableModel.setDataVector(data,columns);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

