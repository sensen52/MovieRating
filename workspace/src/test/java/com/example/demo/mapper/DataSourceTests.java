package com.example.demo.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;


@SpringBootTest
@WebAppConfiguration
public class DataSourceTests {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Test
    public void testConnection() {

        try(
                Connection con = dataSource.getConnection();
                SqlSession session = sqlSessionFactory.openSession();

        ){

            System.out.println("con = " + con);
            System.out.println("session = " + session);

        } catch(Exception e) {

            e.printStackTrace();

        }

    }





}
