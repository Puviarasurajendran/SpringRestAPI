package com.zoho.spring.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.zoho.spring.model.Customer;

@Component
public interface CustomersInterface {

	abstract List<Customer> getAllCustomers();
	abstract Map<String,Object> getCustomerByID_v1(int id);
	abstract void createCustomer_v1(Customer c);
	abstract void updateCustomer_v1(Customer c);
	abstract void deleteCustomer_v1(int id);
    abstract List<Map<String, Object>> getTotalCutomers_v1();

}
