package api.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Request {
	@Test
	//validate Headers
	public void googleMapTest() {
		// Specify Base URI
		RestAssured.baseURI = "https://maps.googleapis.com";// URI

		// REquest Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s\r\n" + 
				"");

		// Extract body from response
		String responseBody = response.getBody().asString();
		System.out.println("responseBody ::" + responseBody);

		 //validating headers
		  String contentType=response.header("Content-Type");// capture details of Content-Type header
		  System.out.println("Content Type is:"+contentType);
		  Assert.assertEquals(contentType, "application/xml; charset=UTF-8");
		  
		  String contentEncoding=response.header("Content-Encoding");// capture details of Content-Encoding  header
		  System.out.println("Content Encoding is:"+contentEncoding);
		  Assert.assertEquals(contentEncoding, "gzip");
		
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
