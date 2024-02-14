package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.example.model.Employee_Model;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Utils {
    public static String setEnvVariable(String variable) throws IOException, ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty("token", variable);
        config.save();
        return variable;

    }

    public static Response get(String uri){
        Response get_response = given().log().all()
                    .contentType("application/json")
                    .auth().preemptive().basic("", "").get(uri);

        return get_response;
    }

    public static Response post(String uri, String payload){
        Response post_response;
        try{
            post_response = given().log().all()
                    .contentType("application/json")
                    .queryParam("", "")
                    .auth().preemptive().basic("", "")
                    .body(payload).get(uri);
        } catch(Exception e){
            post_response = null;
        }
        return post_response;
    }

    public static Response put(String uri, String payload){
        Response put_response;
        try{
            put_response = given().log().all()
                    .contentType("application/json")
                    .queryParam("", "")
                    .auth().preemptive().basic("", "")
                    .body(payload).get(uri);
        } catch(Exception e){
            put_response = null;
        }
        return put_response;
    }

    public static Response delete(String uri){
        Response delete_response;
        try{
            delete_response = given().log().all()
                    .contentType("application/json")
                    .queryParam("", "")
                    .auth().preemptive().basic("", "").get(uri);
        } catch(Exception e){
            delete_response = null;
        }
        return delete_response;
    }

    public static Object mapper(Class Employee_Model, String jsonReqBodyPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee_Model employee = objectMapper.readValue(new File(jsonReqBodyPath), Employee_Model.class);
        return employee;
    }
}
