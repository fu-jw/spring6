package com.fredo.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class JdbcTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 增、删、改操作
     */
    @Test
    public void test1(){
        // 添加
//        String sqlStr = "INSERT into t_emp VALUES(NULL,?,?,?)";
//        int rows = jdbcTemplate.update(sqlStr, "fu-jw", 22, "1");
//        System.out.println(rows);

        // 修改
//        String sql = "update t_emp set age =? where id=?";
//        int rows = jdbcTemplate.update(sql, 34,1);
//        System.out.println(rows);

        // 删除
        String sql = "delete from t_emp where id=?";
        int rows = jdbcTemplate.update(sql, 4);
        System.out.println(rows);
    }

    /**
     * 查询操作
     *  1.返回对象
     */
    @Test
    public void test2(){
        // 返回对象
//        String sql = "select * from t_emp where id=?";
//        Emp emp = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Emp.class),1);
//        System.out.println(emp);

        // 返回list
//        String sql = "select * from t_emp;";
//        List<Emp> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
//        System.out.println(list);

        // 返回单个值
        String sql = "select count(id) from t_emp";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }
}
