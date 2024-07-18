package com.zoho.spring.errorhandler;

import com.zoho.util.ResponseCode;

public class CustomExceptionHandler extends Exception{

    private ResponseCode code;
    private Object feild;
    private String message;
    private String status;


    public CustomExceptionHandler(ResponseCode code, Object feild, String message, String status){
        this.code = code;
        this.feild = feild;
        this.message = message;
        this.status = status;
    }

    public CustomExceptionHandler(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ResponseCode getCode() {
        return code;
    }

    public Object getFeild() {
        return feild;
    }

    public void setFeild(Object feild) {
        this.feild = feild;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setCode(ResponseCode code){
        this.code = code;
    }

    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String toString(){
        return "CustomExceptionHandler [code=" + code + ", feild=" + feild + ", message=" + message + ", status="
                                        + status + "]";
    }



}
