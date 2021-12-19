package com.msskx.java.test;

import com.msskx.java.util.DButil;
import org.junit.Test;

public class DButliTest {
    /**
     * 测试数据库是否连通
     */
    @Test
    public void test01(){
        System.out.println(DButil.getConnection());
    }
}
