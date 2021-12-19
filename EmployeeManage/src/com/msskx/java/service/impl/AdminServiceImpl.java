package com.msskx.java.service.impl;



import com.msskx.java.model.AdminDO;
import com.msskx.java.service.AdminService;
import com.msskx.java.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean validata(AdminDO adminDO) {//传入一个管理对象

        String sql="select password from admin where username=?";//

        PreparedStatement ps=null;
        Connection conn=null;
        ResultSet resultSet=null;

        try {
            conn= DButil.getConnection();
            if (conn == null) {
                return false;
            }
            ps=conn.prepareStatement(sql);//创建一个 PreparedStatement对象，用于将参数化的SQL语句发送到数据库。
            ps.setString(1, adminDO.getUserName());//将指定的参数设置为给定的tring值。
            resultSet=ps.executeQuery();
            while(resultSet.next()){//按行读取返回的数据结果集
                String DBpassword=resultSet.getString(1);
                if(adminDO.getPwd().equals(DBpassword)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeRS(resultSet);
            DButil.closeConnection(conn);
            DButil.closePS(ps);
        }
        return false;
    }

    @Override
    public boolean employeevalidata(AdminDO adminDO) {
        String sql="select e_password from user where e_user=?";//

        PreparedStatement ps=null;
        Connection conn=null;
        ResultSet resultSet=null;

        try {
            conn= DButil.getConnection();
            if (conn == null) {
                return false;
            }
            ps=conn.prepareStatement(sql);//创建一个 PreparedStatement对象，用于将参数化的SQL语句发送到数据库。
            ps.setString(1, adminDO.getUserName());//将指定的参数设置为给定的tring值。
            resultSet=ps.executeQuery();
            while(resultSet.next()){//按行读取返回的数据结果集
                String DBpassword=resultSet.getString(1);
                if(adminDO.getPwd().equals(DBpassword)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeRS(resultSet);
            DButil.closeConnection(conn);
            DButil.closePS(ps);
        }
        return false;
    }

    @Override
    public boolean existdata(AdminDO adminDO) {
        String sql="select * from user where e_user=?";//

        PreparedStatement ps=null;
        Connection conn=null;
        ResultSet resultSet=null;

        try {
            conn= DButil.getConnection();
            if (conn == null) {
                return false;
            }
            ps=conn.prepareStatement(sql);//创建一个 PreparedStatement对象，用于将参数化的SQL语句发送到数据库。
            ps.setString(1, adminDO.getUserName());//将指定的参数设置为给定的tring值。
            resultSet=ps.executeQuery();
            while(resultSet.next()){//按行读取返回的数据结果集
                String DBusername=resultSet.getString(1);
                if(adminDO.getUserName().equals(DBusername)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeRS(resultSet);
            DButil.closeConnection(conn);
            DButil.closePS(ps);
        }
        return false;
    }

    @Override
    public boolean registerdata(AdminDO adminDO) {
        String sql="insert into user(e_user,e_password) values(?,?)";//

        PreparedStatement ps=null;
        Connection conn=null;


        try {
            conn= DButil.getConnection();
            if (conn == null) {
                return false;
            }
            ps=conn.prepareStatement(sql);//创建一个 PreparedStatement对象，用于将参数化的SQL语句发送到数据库。
            ps.setString(1, adminDO.getUserName());//将指定的参数设置为给定的tring值。
            ps.setString(2, adminDO.getPwd());//将指定的参数设置为给定的tring值。
            return ps.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DButil.closeConnection(conn);
            DButil.closePS(ps);
        }
        return false;
    }
}
