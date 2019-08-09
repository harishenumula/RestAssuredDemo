package api.demo;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC2_POST_Request {
	@Test
	public void registerCustomer() {
		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		// REquest Object
		RequestSpecification httpRequest = RestAssured.given();

		// specifying request payload in JSON Format
		org.json.simple.JSONObject requestParams = new org.json.simple.JSONObject();
		requestParams.put("FirstName", "p1avan888");
		requestParams.put("LastName", "Ku1mar88");
		requestParams.put("UserName", "pava1nuser888");
		requestParams.put("Password", "pava1n123pwd888");
		requestParams.put("Email", "wertadgsa1@gmail.com");

		// Add a header stating the request body
		httpRequest.header("conten-Type", "application/json");

		// Add the json body of Request
		httpRequest.body(requestParams.toString());

		// post the Request and check the Response
		Response response = httpRequest.request(Method.POST, "/register");

		// printing Response
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());

		// Validation
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(response.getStatusCode(), 201);
		// JsonPath jpath = response.jsonPath();
		System.out.println(response.jsonPath().get("FaultId"));
		// Validate respponse Success Code
		// String successCode = response.jsonPath().get("SuccessCode");
		System.out.println((org.json.simple.JSONObject) response.getBody().jsonPath().get("FaultId"));
		// softassert.assertEquals(successCode, "OPERATION_SUCCESS");
		softassert.assertAll();
	}

}
