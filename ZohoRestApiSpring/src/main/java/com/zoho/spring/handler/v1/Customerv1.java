package com.zoho.spring.handler.v1;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.zoho.spring.annotations.Age;
import com.zoho.spring.model.Customers;


//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customerv1 extends Customers{

    
    private int id;
    @NotNull(message="First name can't be null")
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Age(lower=20,upper=60,message="Give me a proper age")
    private Integer age;
    private String country;

    public Customerv1(int id, String firstName, String lastName, String email, Integer age, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.country = country;
    }

    public Customerv1() {

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    @Override
    public String toString(){
        return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                                        + ", age=" + age + ", country=" + country + "]";
    }

 

    
}