package com.crm.objectmodel;

import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


public class DynamicSerializerUtil extends JsonSerializer<Object>{

    public DynamicSerializerUtil() {
        this(null);
        System.out.println("DynamicSerializerUtil Object Created  ");
    }

    public DynamicSerializerUtil(Object object){

    }

    @Override
    public void serialize(Object obj, JsonGenerator generator, SerializerProvider serilizer) throws IOException{

        generator.writeStartObject();
        for(Field field: obj.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try{
                Object value= field.get(obj);
               if(field.getType().equals(Double.class)) {
                       if(!value.equals(0.0)) {
                           generator.writeObjectField(field.getName(), value);
                       }
                    }
               else if(value!=null ) {
                    
                    generator.writeObjectField(field.getName(), value);
                }
            } catch(IllegalArgumentException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
        generator.writeEndObject();
    }

}
