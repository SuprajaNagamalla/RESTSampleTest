package com.automation.restapi.tests.apple;

import com.automation.restapi.utils.FileNameConstants;
import com.automation.restapi.utils.RestAPIHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/*A. Create a test to verify an http status code.
The test case is 
1. Send the http request by using the GET method. 
The URL is https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/index.html#//apple_ref/doc/uid/TP40017632-CH3-SW1
2. Validation: status code of the obtained response is 200 OK*/

public class VerifyAppleHttpStatusCode {

	private RequestSpecification httpReq;
	private Response resp;
	private RestAPIHelper apiHelper;
	private int successStatusCode = 200;

	FileNameConstants fileNameConstants;

	@BeforeClass(alwaysRun = true)
	public void setup() throws InterruptedException {
		RestAssured.baseURI = fileNameConstants.BASE_URL;
		httpReq = RestAssured.given();
	}


	@Test
	public void verifyStatusCode() {
		resp = httpReq.get(fileNameConstants.AUDIOVIDEOiTUNESEARCHENDPOINT);
		int code = resp.getStatusCode();
		assertEquals(code, successStatusCode);
	}

}
