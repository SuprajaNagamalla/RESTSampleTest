package com.automation.restapi.tests.apple;

import com.automation.restapi.utils.FileNameConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.core.util.FileUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;

/*С. Create a test to verify an http response body.
The test case is 
1. Send the http request by using the GET method. 
The URL is https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/index.html#//apple_ref/doc/uid/TP40017632-CH3-SW1
2. Validation: the content of the response body is the array of 10 users.
*/

public class VerifyAppleResponseBodyTest {
	private int successStatusCode = 200;

	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "https://developer.apple.com";
		RestAssured.basePath = "/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI";
//		https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/index.html#//apple_ref/doc/uid/TP40017632-CH3-SW1
	}

	@Test
	public void verifyStatusCode() {
		given().contentType(ContentType.HTML).when().get().then().assertThat().statusCode(successStatusCode);
	}

	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifyResponseTitle() {
		String expectedTitle = "iTunes Search API: Overview";
		String title = given().contentType(ContentType.HTML).when().get().then().extract().response().htmlPath().getString("html.head.title");
		Assert.assertEquals(title, expectedTitle);

	}

	@Test(dependsOnMethods = { "verifyStatusCode" })
	public void verifyResponseBody() {
		Response response = given().contentType(ContentType.HTML).when().get().then().extract().response();
		XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
		String overview = "The Search API allows you to place search fields in your website to search for content within the iTunes Store, App Store, iBooks Store and Mac App Store. You can search for a variety of content; including apps, iBooks, movies, podcasts, music, music videos, audiobooks, and TV shows. You can also call an ID-based lookup request to create mappings between your content library and the digital catalog. Developers may use promotional content in the API, including previews of songs, music videos, album art and App icons only to promote store content and not for entertainment purposes. Use of sound samples and other assets from the API must be proximate to a store badge. Terms and conditions apply.";
		Assert.assertTrue(htmlPath.getString("html.body").contains(overview));

	}

}
