package com.automation.restapi.utils;

import com.automation.restapi.listener.RestAssuredListener;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class RestAPIHelper {

    public Response postAPIRequest(String uri, String requestBody, Header header) {
        return RestAssured.given().contentType(ContentType.JSON).body(requestBody).baseUri(uri).when().post().then()
                .assertThat().statusCode(200).extract().response();
    }

    public Response getAPIRequest(String uri, String requestBody, Header header) {
        return RestAssured.given().contentType(ContentType.JSON).baseUri(uri).when().get().then().assertThat()
                .statusCode(200).extract().response();
    }

//    public int getAPIRequestHTMLTypeStatusCode(String uri, String endPoint) {
//        return RestAssured.given().contentType(ContentType.HTML).baseUri(uri).when().get(endPoint).statusCode();
//    }
    public Response putAPIRequest(String uri, String requestBody, Header header) {
        return RestAssured.given().contentType(ContentType.JSON).body(requestBody).header(header).baseUri(uri).when()
                .put().then().assertThat().statusCode(200).extract().response();
    }

    public Response patchAPIRequest(String uri, String requestBody, Header header) {
        return RestAssured.given().filter(new RestAssuredListener()).contentType(ContentType.JSON).body(requestBody)
                .header(header).baseUri(uri).when().patch().then().assertThat().statusCode(200).extract().response();
    }

    public Response deleteAPIRequest(String uri, String requestBody, Header header) {
        return RestAssured.given().filter(new RestAssuredListener()).contentType(ContentType.JSON).header(header)
                .baseUri(uri).when().delete().then().extract().response();
    }

}
