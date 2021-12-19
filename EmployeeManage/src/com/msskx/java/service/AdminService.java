package com.msskx.java.service;


import com.msskx.java.model.AdminDO;

public interface AdminService {
    boolean validata(AdminDO adminDO);//校验用户登录信息
    boolean employeevalidata(AdminDO adminDO);//校验用户登录信息
    boolean existdata(AdminDO adminDO);//判断用户是否存在
    boolean registerdata(AdminDO adminDO);//新增用户
}
