package com.roadjava.student.table;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableModel extends DefaultTableModel {
    static Vector<String> columns=new Vector<>();

    public static Vector<String> getColumns() {
        return columns;
    }

    static{
        columns.addElement("编号");
        columns.addElement("姓名");
        columns.addElement("工号");
        columns.addElement("户籍");
        columns.addElement("coding");
        columns.addElement("work");
        columns.addElement("play");
    }
    private MainViewTableModel(){
        super(null,columns);
    }
    private static MainViewTableModel mainViewTableModel=new MainViewTableModel();
    public static MainViewTableModel assembleModel(Vector<Vector<Object>>data){
        mainViewTableModel.setDataVector(data,columns);
        return mainViewTableModel;
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
