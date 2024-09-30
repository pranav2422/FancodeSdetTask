package com.fancode.utilities;

import io.restassured.response.Response;

public class JasonResponseToArray {
	
	public static <T> T[] convertResponseToArray(Response response, Class<T[]> responseType) {
        return response.getBody().as(responseType);
    }

}
