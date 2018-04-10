package com.flour_power_bottom;
import java.util.HashMap;
import java.util.Map;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.RequestBodyEntity;
import com.google.gson.*;

public class requestsDemo
{
    public static void main(String[] args) throws Exception
    {
	requestsDemo client = new requestsDemo();
	String JWT_TOKEN = client.getAuthToken();
	
	
	Unirest.shutdown();
    }
    
    public void getQuestionsUsingUnirest() throws Exception 
    {
	HttpResponse<JsonNode> response = Unirest.get("https://api.stackexchange.com/2.2/questions").
		header("accept", "application/json").
		queryString("order", "desc").
		queryString("sort", "creation").
		queryString("filter", "default").
		queryString("site", "stackoverflow").
		asJson();
	System.out.println(response.getBody().getObject().toString());
    }
    
    public String getAuthToken() throws Exception
    {
	HttpResponse<JsonNode> response = Unirest.post("https://api.thetvdb.com/login").
		header("accept", "application/json").
		header("Content-Type", "application/json").
		body("{\"apikey\":\"80CE1FE530B67E3F\",\"userkey\":\"B51F0075273DAD57\",\"username\":\"Lutalo\"}").
		asJson();
	
	
//	System.out.println(response.getStatus());
	
	System.out.println(response.getBody());
	if()
	Gson g = new Gson();
	Map m = g.fromJson(response.getBody().toString(), Map.class);
	return (String) (m.get("token"));
    }

}
