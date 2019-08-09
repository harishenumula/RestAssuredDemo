package api.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_ValidatingJSONResponse {
	@Test
	public void GetWeatherDetails() {
		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";// URI

		// REquest Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/Delhi");

		// Extract body from response
		String responseBody = response.getBody().asString();
		System.out.println("responseBody ::" + responseBody);
		Assert.assertEquals(responseBody.contains("Delhi"), true);

	}

}
