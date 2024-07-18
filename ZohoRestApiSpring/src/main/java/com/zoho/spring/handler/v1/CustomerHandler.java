package com.zoho.spring.handler.v1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zoho.spring.dao.CustomersDAO;
import com.zoho.spring.errorhandler.CustomExceptionHandler;
import com.zoho.spring.model.Customer;
import com.zoho.spring.model.Customers;


@Service("customerHandlerv1")
public class CustomerHandler {

	@Autowired
	CustomersDAO customersDAO;
	
    @Autowired
    private Validator validator;

	public List<Customers> getTotalCutomers() {

	    List<Map<String, Object>> customersMapList = customersDAO.getTotalCutomers_v1();
	    List<Customers> customers = customersMapList.stream()
	            .map(customerMap -> new Customerv1(
	                    (int) customerMap.get("id"),
	                    (String) customerMap.get("firstName"),
	                    (String) customerMap.get("lastName"),
	                    (String) customerMap.get("email"),
	                    (int) customerMap.get("age"),
	                    (String) customerMap.get("country")
	            ))
	            .collect(Collectors.toList());

	    return customers;
	}

	    public Map<String, Object> getCustomerByID(int id) {

	        return customersDAO.getCustomerByID_v1(id);
	    }

	    public void createCustomer(@Valid Customers c) throws Exception {
	        if(validatePojo(c)) {
	            System.out.println("Inside v1 Handler layer " + c);
	            }
	        
//	        customersDAO.createCustomer_v1(c);
	    }


	    public void updateCustomer(Customer c) {
	        customersDAO.updateCustomer_v1(c);

	    }

	    public void deleteCustomer(int id) {
	        customersDAO.deleteCustomer_v1(id);
	    }
	    
	    public boolean validatePojo(Customers c) throws Exception {       
	            Errors errors = new BeanPropertyBindingResult(c, "Customerv1");
	            ValidationUtils.invokeValidator(getValidator(), c, errors);

	            if (errors.hasErrors()) {
	                // If there are validation errors, handle them appropriately
	                String errorMessages = errors.getAllErrors().stream()
	                                            .map(error -> error.getDefaultMessage())
	                                            .collect(Collectors.joining("\n"));
	                System.out.println("Validate pojo :"+errorMessages);
	                
	                throw new Exception("Validation errors: \n" + errorMessages);
	            }
	    
	        return true;
	    }

        public Validator getValidator(){
            return validator;
        }

        public void setValidator(Validator validator){
            this.validator = validator;
        }

        public CustomersDAO getCustomersDAO(){
            return customersDAO;
        }

        public void setCustomersDAO(CustomersDAO customersDAO){
            this.customersDAO = customersDAO;
        }
        
        
}

