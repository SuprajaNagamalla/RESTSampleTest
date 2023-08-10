package com.automation.restapi.tests.json;

import static org.testng.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/*A. Create a test to verify an http status code.
The test case is 
1. Send the http request by using the GET method. 
The URL is https://jsonplaceholder.typicode.com/users
2. Validation: status code of the obtained response is 200 OK*/

public class VerifyHttpStatusCode {

	private RequestSpecification httpReq;
	private Response resp;
	private int successStatusCode = 200;

	@BeforeClass(alwaysRun = true)
	public void setup() throws InterruptedException {
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		httpReq = RestAssured.given();

	}

	
	@Test
	public void verifyStatusCode() {
		resp = httpReq.get("/users");
		int code = resp.getStatusCode();
		assertEquals(code, successStatusCode);

	}
	
	 @Test
	    public void verifyStatusCodeUsingBddApproach() {
		 httpReq. get("/users").
	        then().
	                assertThat().statusCode(successStatusCode);
	    }
	 
	 
	 @Test
	    public void verifyStatusCodeUsingResponseAndHamcrestValidation() {
	        resp = httpReq.get("/users");
	        int statusCode = resp.getStatusCode();
	        assertThat(statusCode, equalTo(HttpStatus.SC_OK));
	    }
	 
	 @Test
	    public void verifyStatusCodeUsingRequestAndResponseSpecification() {
		 RequestSpecification requestSpec = new RequestSpecBuilder().build();
	        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(successStatusCode).build();
	        httpReq.spec(requestSpec)
	        .get("/users").
	        then().
	                spec(responseSpecification);
	    }

	@Test // another way to validate to status code
	public void verifyStatusCodeWithDetails() {
		httpReq.get("/users").then().assertThat().log().all().statusCode(successStatusCode);

	}

}
