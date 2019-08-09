package api.demo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Tc004_GEt_Request_printAllHeaders {
	@Test
	public void GetWeatherDetails() {
		// Specify Base URI
		RestAssured.baseURI = "https://maps.googleapis.com";// URI

		// REquest Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET,
				"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s\r\n"
						+ "");

		// Extract body from response
		String responseBody = response.getBody().asString();
		System.out.println("responseBody ::" + responseBody);

		//Capturing all the Headers for Response
		Headers allheaders = response.getHeaders();

		for (Header header : allheaders) {
			System.out.println(header.getName() + " :: " + header.getValue());
		}

	
	}
}
