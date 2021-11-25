package test;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class JtableDemo extends JFrame {
    public JtableDemo() {
        super ("TestJTable");
//        Vector<String>columns=new Vector<>();
//        columns.addElement("编号");
//        columns.addElement("姓名");
//        columns.addElement("工号");
//        columns.addElement("户籍");
//        columns.addElement("coding");
//        columns.addElement("work");
//        columns.addElement("play");

        Vector<Vector<Object>>data=new Vector<>();

        Vector<Object>row1=new Vector<>();
        row1.addElement("1");
        row1.addElement("mashuai");
        row1.addElement("ms1");
        row1.addElement("陕西");
        row1.addElement("0");
        row1.addElement("0");
        row1.addElement("100");
        Vector<Object>row2=new Vector<>();
        row2.addElement("2");
        row2.addElement("mashuai");
        row2.addElement("ms1");
        row2.addElement("陕西");
        row2.addElement("0");
        row2.addElement("0");
        row2.addElement("100");
        Vector<Object>row3=new Vector<>();
        row3.addElement("3");
        row3.addElement("mashuai");
        row3.addElement("ms1");
        row3.addElement("陕西");
        row3.addElement("0");
        row3.addElement("0");
        row3.addElement("100");

        data.addElement(row1);
        data.addElement(row2);
        data.addElement(row3);


        //tablemodel和jtable关联后之后只需要更新model就能把数据的变化反应到jtable中
        StudentTablemodel tableModel=StudentTablemodel.assembleModel(data);
        //jtable和table关联
        JTable jTable=new JTable(tableModel);
        //设置表头
        JTableHeader tableHeader= jTable.getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.RED);
        //设置表格体
        jTable.setFont(new Font(null, Font.PLAIN,14));
        jTable.setForeground(Color.CYAN);
        jTable.setGridColor(Color.BLACK);
        jTable.setRowHeight(60);
        //设置多行选择
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //设置表格列的渲染格式
        Vector<String> columns=StudentTablemodel.getColumns();
        StudentCellRender render=new StudentCellRender();
        for(int i=0;i< columns.size();i++){
            TableColumn column=jTable.getColumn(columns.get(i));
            column.setCellRenderer(render);
            if(i==0){
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
                column.setResizable(false);
            }
        }

        Container container=getContentPane();
        JScrollPane jScrollPane=new JScrollPane(jTable);
        container.add(jScrollPane);

        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new JtableDemo();
    }
}
class StudentTablemodel extends DefaultTableModel{
    static Vector<String>columns=new Vector<>();

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
    private StudentTablemodel(){
        super(null,columns);
    }
    private static StudentTablemodel studentTablemodel=new StudentTablemodel();
    public static StudentTablemodel assembleModel(Vector<Vector<Object>>data){
        studentTablemodel.setDataVector(data,columns);
        return studentTablemodel;
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
class StudentCellRender extends DefaultTableCellRenderer{
    //在每一行每一列显示

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(row%2==0){
            setBackground(Color.LIGHT_GRAY);
        }else{
            setBackground(Color.WHITE);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
