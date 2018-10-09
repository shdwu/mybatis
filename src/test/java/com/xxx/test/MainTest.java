package com.xxx.test;

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
import java.util.Properties;

/**
 * @author: shdwu
 * @date: 18-9-26 11:33
 * @description:
 */
public class MainTest {

    @Test
    public void mainTest() throws IOException {
        String resource = "resources/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        Customer customer = new Customer();
        customer.setName("cust1");
        customer.setRegion("Chongqing");
        try(SqlSession session = sqlSessionFactory.openSession()) {
            int i = session.insert("com.xxx.test.dao.CustomerDao.insertCustomer",customer);
            System.out.println("i======="+ i);
            session.commit();
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
