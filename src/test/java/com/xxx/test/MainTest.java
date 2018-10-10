package com.xxx.test;

import com.xxx.test.dao.CustomerDao;
import com.xxx.test.domain.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * @author: shdwu
 * @date: 18-9-26 11:33
 * @description:
 */
public class MainTest {

    public SqlSessionFactory initMybatis() {
        String resource = "resources/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void mainTest() {
        SqlSessionFactory sqlSessionFactory = initMybatis();
        Customer customer = new Customer();
        customer.setName("cust1");
        customer.setRegion("Chongqing");
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int i = session.insert("com.xxx.test.dao.CustomerDao.insertCustomer", customer);
            System.out.println("i=======" + i);
        }
    }

    @Test
    public void mapperTest() {
        SqlSessionFactory sqlSessionFactory = initMybatis();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CustomerDao customerDao = session.getMapper(CustomerDao.class);
            Customer customer = customerDao.selectCustomer(2L);
            System.out.println(customer.toString());
        }
    }

    @Test
    public void testSPIClassLoader() {
        Enumeration<Driver> enumeration = DriverManager.getDrivers();
        Driver driver;
        while (enumeration.hasMoreElements()) {
            driver = enumeration.nextElement();
            System.out.println(driver.getClass() + "--------------" + driver.getClass().getClassLoader());
        }
        System.out.println(DriverManager.class.getClassLoader());
    }

}
