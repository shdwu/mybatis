package com.xxx.test.service;


import com.xxx.test.domain.Customer;

import javax.persistence.EntityManager;

/**
 * @author: shdwu
 * @date: 18-9-27 10:21
 * @description:
 */
public class CustomerService {

    private EntityManager entityManager;

    public void persist(Customer customer) {
        entityManager.persist(customer);
    }

    public void removeAll() {
        entityManager.createQuery("DELETE FROM Customer c");
    }
}
