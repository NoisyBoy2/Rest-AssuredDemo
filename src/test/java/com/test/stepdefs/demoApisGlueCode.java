package com.test.stepdefs;
import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class demoApisGlueCode {
	Integer statusCode;
	String contentType;
	String profileName;
	String ResponseBody;
	
	@Given("user will Fetch the profile details from url as {string}")
	public void user_will_Fetch_the_profile_details_from_url_as(String url) {
		RestAssured.baseURI =url;
		 RequestSpecification request = RestAssured.given();
		 
		 JSONObject requestParams = new JSONObject();
		 request.body(requestParams.toString());
		 request.header("Accept","application/json");
		 request.header("Content-Type","application/json");
		 Response response = request.get("");
		 
		 statusCode = response.getStatusCode();
		 ResponseBody = response.getBody().asString();
	}
	
	@Given("user will post the student details resuest as")
	public void user_will_post_the_student_details_resuest_as(io.cucumber.datatable.DataTable dataTable) {
		 RestAssured.baseURI ="https://my-json-server.typicode.com/NoisyBoy2/Rest-AssuredDemo";
		 RequestSpecification request = RestAssured.given();
		 JSONObject requestParams = new JSONObject(dataTable);
		 request.body(requestParams.toString());
		 request.header("charset","UTF-8");
		 request.header("Content-Type","application/json");
		 Response response = request.post("/Students");
		 statusCode = response.getStatusCode();
		 ResponseBody = response.getBody().asString();
	}
	
	@Given("user will delete the comment that has been added")
	public void user_will_delete_the_comment_that_has_been_added() {
		RestAssured.baseURI = "https://my-json-server.typicode.com/NoisyBoy2/Rest-AssuredDemo/Students";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.DELETE, "/1");
	    statusCode = response.getStatusCode();
	}
	
	@Then("user will verify the response code as {string} from fake server")
	public void user_will_verify_the_response_code_as_from_fake_server(String responseCode) {
		System.out.println("Response Code is =>  " + statusCode);
		Assert.assertEquals(statusCode.toString(), responseCode);
	}
	
	@Then("user profile name as {string}")
	public void user_profile_name_as(String name) {
		Assert.assertTrue(ResponseBody.contains(name));
	}


}
