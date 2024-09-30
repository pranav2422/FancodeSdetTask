package com.fancode.methodimplementation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class getMethod {

	public static Response getMethod(String enpoint) {
		Response res = given()
				.contentType(ContentType.JSON)
				.when()
				   .get(enpoint);
		return res;

	}

	
	
	/*
	 * public static Response postMethod() { return null;
	 * 
	 * }
	 */

}
