package api.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {
	@Test
	public void getweatherDetails() {
		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";// URI

		// REquest Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/Hyderabad");

		// Extract body from response
		String responseBody = response.getBody().asString();
		System.out.println("responseBody ::" + responseBody); 
 
		// Extract status from response
		int statusCode = response.getStatusCode();
		System.out.println("StatusCode  ::" + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// Extract status line from response
		System.out.println(response.getStatusLine());

		// Validation
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(statusCode, 200);
	}
}
