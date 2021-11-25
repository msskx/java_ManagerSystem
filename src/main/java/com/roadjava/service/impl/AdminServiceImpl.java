package com.roadjava.service.impl;

import com.roadjava.entity.AdminDO;
import com.roadjava.service.AdminService;
import com.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {

    public boolean validateAdmin(AdminDO adminDO) {
        String userName= adminDO.getUserName();
        String pwdParam= adminDO.getPwd();
        String sql="select pwd from manager where user_name=?";

        PreparedStatement ps=null;
        Connection conn= null;
        ResultSet resultSet=null;

        try {
            conn= DBUtil.getConn();
            if(conn==null){
                return false;
            }
            ps= conn.prepareStatement(sql);
            ps.setString(1,adminDO.getUserName());
            resultSet= ps.executeQuery();
            while(resultSet.next()){
                String pwd=resultSet.getString(1);
                if(adminDO.getPwd().equals(pwd)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeRs(resultSet);
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
        }
        return false;
    }

}
