package com.crm.objectmodel;

import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class DynamicDeserializerUtil extends StdDeserializer<Object>{

    private Class<?> targetClass;

    public DynamicDeserializerUtil(Class<?> c){
        super(c);
        this.targetClass=c;

    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
        System.out.println("Inside DynamicDeserializerUtil in ZohoRestApiSpring ><>><><><><><>");
       JsonNode node =p.getCodec().readTree(p);
       Object obj;

       try{
        obj=targetClass.newInstance();

        for(Field field: targetClass.getDeclaredFields()){
            field.setAccessible(true);
            JsonNode fieldNode =node.get(field.getName());
            if(fieldNode!=null) {

                if(field.getType().equals(String.class)) {
                    field.set(obj, fieldNode.asText());
                    }
                else if(field.getType().equals(Integer.class)) {
                    field.set(obj, fieldNode.asInt());
                    }
                else if(field.getType().equals(Boolean.class)) {
                    field.set(obj, fieldNode.asText());
                    }
                else if(field.getType().equals(Double.class)) {
                    field.set(obj, fieldNode.asText());
                    }
            }
        }

    } catch(InstantiationException | IllegalAccessException e){

        e.printStackTrace();
    }
        return null;
    }

}
