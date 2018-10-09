package com.xxx.test.dao;

import com.xxx.test.domain.Customer;

/**
 * @author: shdwu
 * @date: 18-9-27 14:08
 * @description:
 */
public interface CustomerDao {

    Customer selectCustomer(Long id);

    Customer insertCustomer(Customer customer);
}
