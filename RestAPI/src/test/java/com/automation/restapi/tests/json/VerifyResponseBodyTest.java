package com.automation.restapi.tests.json;

import java.util.List;
import java.util.stream.Stream;

import com.automation.restapi.model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static io.restassured.RestAssured.given;

/*С. Create a test to verify an http response body.
The test case is 
1. Send the http request by using the GET method. 
The URL is https://jsonplaceholder.typicode.com/users
2. Validation: the content of the response body is the array of 10 users.
*/

public class VerifyResponseBodyTest {
	private int successStatusCode = 200;
	private String contentType = "application/json; charset=utf-8";

	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		RestAssured.basePath = "users";
	}

	@Test
	public void verifyStatusCode() {
		given().when().get().then().assertThat().statusCode(successStatusCode);
	}

	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifySizeOfTheResponseUsingSizeMethod() {
		given().when().get().then().body("size()", is(10));
	}

	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifySizeOfTheResponseUsingJsonPath() {
		Response response = given().when().get();
		List<String> root = response.jsonPath().getList("$");
		assertThat(root.size(), is(10));
	}

	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifySizeOfTheResponseUsingPOJO() {
		Response response = given().when().get();
		User[] users = response.as(User[].class);
		assertThat(users.length, is(10));
	}

	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifySizeOfTheResponseUsingPOJOAndJsonPath() {
		Response response = given().when().get();
		List<User[]> users = response.then().extract().body().jsonPath().getList("$");
		assertThat(users.size(), is(10));
	}

	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifySizeOfTheResponseUsingResponseAsStringAndJsonPath() {
		String responseAsString = given().when().get().asString();
		List<User[]> users = JsonPath.from(responseAsString).getList("$");
		assertThat(users.size(), is(10));
	}

	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifySizeOfTheResponseUsingJSonPath() {
		Response response = given().when().get();
		JsonPath jsonPath = new JsonPath(response.asString());
		List<User[]> users = jsonPath.getList("$");
		assertThat(users.size(), is(10));
	}

	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void printAllTheUsers() {
		Response response = given().when().get();
		User[] users = response.as(User[].class);
		Stream.of(users).forEach(System.out::println);
	}

}
