package com.zoho.spring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crm.objectmodel.DynamicSerializerUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.zoho.spring.errorhandler.CustomExceptionHandler;
import com.zoho.spring.handler.v1.CustomerHandler;
import com.zoho.spring.model.Customer;
import com.zoho.spring.model.Customers;

@RestController
@RequestMapping("/api/{version}/**")
public class CustomerController{

    @Autowired
    private ApplicationContext applicationContext;

    Class<?> pojoClass;

    @GetMapping("/customers")
    public <T> ResponseEntity<T> getAllCustomers(HttpServletRequest request){
        try{
            String handlerName = (String) request.getAttribute("handler");
            CustomerHandler customerHandler = (CustomerHandler) applicationContext.getBean(handlerName, Object.class);
            System.out.println("inside Spring project <><><><>");
            return new ResponseEntity<>((T) serializeObject(customerHandler.getTotalCutomers()), HttpStatus.OK);
        } catch(Exception e){

            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/customers/{id}")
    public <T> ResponseEntity<T> getCustomerById(HttpServletRequest request, @PathVariable("id") int id){
        String handlerName = (String) request.getAttribute("handler");
        CustomerHandler customerHandler = (CustomerHandler) applicationContext.getBean(handlerName, Object.class);
        System.out.println("inside Spring project <><><><>");
        return new ResponseEntity<>((T) serializeObject(customerHandler.getCustomerByID(id)), HttpStatus.OK);

    }

    @PostMapping("/customers")
    public String createCustomer(HttpServletRequest request, @RequestBody JsonNode customer)
                                    throws Exception{
        
 
            String handlerName = (String) request.getAttribute("handler");
            CustomerHandler customerHandler = (CustomerHandler) applicationContext.getBean(handlerName, Object.class);
            String pojoName = (String) request.getAttribute("pojo");
            pojoClass = Class.forName(pojoName);
            Gson gson = new Gson();
            Object c = gson.fromJson(customer.toString(), pojoClass);
            customerHandler.createCustomer((@Valid Customers) c);
            System.out.println("exception createcustomer <><><>><><>><><>>><>>><>>");
            return "Customer Detail Created successfully";
        
        }
    

    @PutMapping("/customers")
    public String updateCustomer(HttpServletRequest request, @Valid @RequestBody Customer updatedCustomer)
                                    throws CustomExceptionHandler{
        try{
            System.out.println("Inside the PUT method of HelloController " + updatedCustomer);
            String handlerName = (String) request.getAttribute("handler");
            CustomerHandler customerHandler = (CustomerHandler) applicationContext.getBean(handlerName, Object.class);
            customerHandler.updateCustomer(updatedCustomer);
            return "Customer Detail Updated successfully";
        } catch(Exception e){
            throw new CustomExceptionHandler(e.getMessage());
        }
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(HttpServletRequest request, @PathVariable("id") int id){
        System.out.println("Inside the DELETE method of HelloController");
        String handlerName = (String) request.getAttribute("handler");
        CustomerHandler customerHandler = (CustomerHandler) applicationContext.getBean(handlerName, Object.class);
        customerHandler.deleteCustomer(id);
        return "Customer with ID " + id + " deleted successfully";
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get( "/home/test-1/Music/" + file.getOriginalFilename());
            Files.write(path, bytes);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    public String serializeObject(Object data){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Customer.class, new DynamicSerializerUtil());
        mapper.registerModule(module);
        String json = "";
        try{
            json = mapper.writeValueAsString(data);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return json;
    }

    public void deserializeObject(Object data){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        // module.addDeserializer(Customer.class,new
        // DynamicDeserializerUtil(Customer.class));
        mapper.registerModule(module);
    }

}