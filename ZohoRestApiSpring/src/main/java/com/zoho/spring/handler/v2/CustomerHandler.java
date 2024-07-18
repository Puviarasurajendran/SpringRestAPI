package com.zoho.spring.handler.v2;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.zoho.spring.errorhandler.CustomExceptionHandler;
import com.zoho.spring.model.Customers;
import com.zoho.util.ResponseCode;

@Service("customerHandlerv2")
public class CustomerHandler extends com.zoho.spring.handler.v1.CustomerHandler{

    // @Override
    // public List<Customers> getTotalCutomers(){
    //
    // System.out.println("enter into version 2 CustomerHandler");
    //
    // List<Customers> customersV2 = super.getTotalCutomers().stream().map(c -> {
    // Customerv2 customerv2 = new Customerv2();
    // customerv2.setId((int) ((Customerv2) c).getId());
    // FullName fn = new FullName();
    // fn.setFirstName( c.getFirstName());
    // fn.setLastName( c.getLastName());
    // customerv2.setFullname(fn);
    // customerv2.setEmail(((Customerv2) c).getEmail());
    // customerv2.setAge(((Customerv2) c).getAge());
    // customerv2.setCountry(((Customerv2) c).getCountry());
    // return customerv2;
    // }).collect(Collectors.toList());
    //
    // return customersV2;
    // }

    public void createCustomer(@Valid Customers c) throws CustomExceptionHandler{
//        if(validatePojo(c)){
            System.out.println("Inside v2 Handler layer " + c);
//            super.getCustomersDAO().createCustomer_v2((Customerv2) c);
//        }
        
    }
    public boolean validatePojo(Customers c) throws Exception{

        Errors errors = new BeanPropertyBindingResult(c, "Customerv2");
        ValidationUtils.invokeValidator(super.getValidator(), c, errors);

        if(errors.hasErrors()){
            // If there are validation errors, handle them appropriately
            String errorMessages = errors.getAllErrors().stream()
                                            .map(error -> error.getDefaultMessage())
                                            .collect(Collectors.joining("\n"));
            System.out.println(errorMessages);
            throw new Exception();
           // throw new CustomExceptionHandler(errorMessages);
        }
//
        return true;
    }
}
