package dataDrivenTesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class DataDrivenTest_AddNewEmployees {
	@Test(dataProvider = "empdataprovider")
	public void postNewEmployee(String ename, String sal, String age) {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		RequestSpecification httpRequest = RestAssured.given();

		// here we created data which we can send along with the request
		JSONObject requestparams = new JSONObject();
		requestparams.put("name", ename);
		requestparams.put("salary", sal);
		requestparams.put("age", age);

		// add a header stating the request body is a JSON.
		httpRequest.header("Content-Type", "application/json");

		// add the json to the body of the request.
		httpRequest.body(requestparams.toJSONString());

		// Post Request
		Response response = httpRequest.request(Method.POST, "/create");

		// capture response body to perform validations
		String responseBody = response.getBody().asString();
		System.out.println("responseBody is " + responseBody);
		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(sal), true);
		Assert.assertEquals(responseBody.contains(age), true);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@DataProvider(name = "empdataprovider1")
	String[][] getEmployee1() {
		String empdata[][] = { { "h3456", "30000", "26" }, { "xyz789", "60000", "27" }, { "poi", "66500", "26" } };
		return empdata;
	}

	@DataProvider(name = "empdataprovider")
	String[][] getEmployee() throws IOException, Throwable {
//		String path = System.getProperty("user.dir") + "/TestData/apiTestData.xlsx";
		ExcelFileUtil xls = new ExcelFileUtil();
		int rowcount = xls.getrowCount("Sheet1");
		int colCount = xls.colcount("Sheet1", 1);

		String empdata[][] = new String[rowcount][colCount];
		// String empdata[][] = { { "h3456", "30000", "26" }, { "xyz789", "60000", "27"
		// }, { "poi", "66500", "26" } };
		for (int i = 1; i <= rowcount; i++) {
			for (int j = 0; j < colCount; j++) {
				empdata[i - 1][j] = xls.getData("Sheet1", i, j);
			}
		}
		return empdata;
	}

}
