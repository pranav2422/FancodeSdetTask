package com.fancode.endpoints;
import io.restassured.RestAssured;
public class Routes {

	public static final String BaseURL = "http://jsonplaceholder.typicode.com";
	
	//modules endpoints
	
	public static final String USERS = BaseURL + "/users";
    public static final String TODOS = BaseURL + "/todos";
}
