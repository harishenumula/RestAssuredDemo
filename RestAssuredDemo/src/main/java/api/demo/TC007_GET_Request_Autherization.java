package api.demo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC007_GET_Request_Autherization {
	@Test
	public void AutherizationTest() {
		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

		// Basic Authentication
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("ToolsQA");
		authscheme.setPassword("TestPassword");

		RestAssured.authentication = authscheme;

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object 
		Response response = httpRequest.request(Method.GET, "/");

		// Print Response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " + responseBody);

		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 200);

	}

}
