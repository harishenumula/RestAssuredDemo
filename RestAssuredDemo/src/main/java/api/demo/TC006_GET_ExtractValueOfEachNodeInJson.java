package api.demo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.JsonPath.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC006_GET_ExtractValueOfEachNodeInJson {
	@Test
	public void GetWeatherDetails() {
		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";// URI

		// REquest Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/Delhi");
		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.get("City"));
		System.out.println(jsonpath.get("Temperature"));
		System.out.println(jsonpath.get("Humidity"));
		System.out.println(jsonpath.get("WeatherDescription"));
		System.out.println(jsonpath.get("WindSpeed"));
		System.out.println(jsonpath.get("WindDirectionDegree"));
		String cityname = jsonpath.get("City");
		Assert.assertEquals(cityname, "Delhi");
	}
}
