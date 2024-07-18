package com.zoho.spring.handler.v2;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.zoho.spring.annotations.Age;
import com.zoho.spring.model.Customers;

public class Customerv2 extends Customers{
    
    private int id;
    @NotNull(message = "fullname can't be null")
    private FullName fullname;
    @Email
    private String email;
    @Age(lower=20,upper=60,message="Give me a proper age")
    private Integer age;
    private String country;
    

    public Customerv2(int id, @NotNull(message = "fullname can't be null") FullName fullname, @Email String email,
                                    Integer age, String country){
        super();
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.age = age;
        this.country = country;
    }
    
    public  Customerv2(){
        
    }

    public FullName getFullname(){
        return fullname;
    }

    public void setFullname(FullName fullname){
        this.fullname = fullname;
    }
    
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
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

    public static class FullName{
        private String firstName;
        private String lastName;

        public FullName(){
        }

        public FullName(String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
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

        @Override
        public String toString(){
            return "FullName [firstName=" + firstName + ", lastName=" + lastName + "]";
        }
    }

    @Override
    public String toString(){
        return "Customerv2 [id=" + id + ", fullname=" + fullname + ", email=" + email + ", age=" + age + ", country="
                                        + country + "]";
    }

    
}
