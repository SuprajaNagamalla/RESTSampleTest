package com.automation.restapi.tests.json;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

/*B. Create a test to verify an http response header.
The test case is 
1. Send the http request by using the GET method. 
The URL is https://jsonplaceholder.typicode.com/users
2. Validation: -the content-type header exists in the obtained response
                         -the value of the content-type header is application/json; charset=utf-8*/
public class VerifyResponseHeaderTest {

	private int successStatusCode = 200;
	private String contentType = "application/json; charset=utf-8";

	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RestAssured.basePath = "/users";
	}

	@Test
	public void verifyStatusCode() {
			given().
				get().
				then().
				assertThat().
				statusCode(successStatusCode);
	}

	
	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifyContentTypeUsingBddApproach() {
		given().when().get().then().assertThat().header("Content-Type", contentType);
	}
	
	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifyContentTypeUsingBddApproachAndTestNGAssertion() {
		String content_Type = given().when().get().thenReturn().getHeader("Content-Type");
		assertEquals(content_Type, contentType);
	}
	
	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifyContentTypeUsingRequestAndResponseSpecification() {
		RequestSpecification requestSpecification = new RequestSpecBuilder().build();
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(contentType).build();
		given().spec(requestSpecification).when().get().then().spec(responseSpecification);
	}

	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifyContentTypeUsingHamcrestValidation() {
		Response response = given().when().get();
		String content_Type = response.getContentType();
		assertThat(content_Type, equalTo(contentType));
	}

	
}
