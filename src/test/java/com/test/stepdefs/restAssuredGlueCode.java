package com.test.stepdefs;
import java.util.Map;
import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class restAssuredGlueCode {
	static String BLobID;
	Integer statusCode;
	String contentType;
	String profileName;
	String ResponseBody;
	
	@Given("user will post the resuest as")
	public void user_will_post_the_resuest_as(io.cucumber.datatable.DataTable dataTable) {
	    RestAssured.baseURI ="https://jsonblob.com/api";
		 RequestSpecification request = RestAssured.given();
		 
		 
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("people",dataTable); // Cast
		 request.body(requestParams.toString());
		 request.header("Accept","application/json");
		 request.header("Content-Type","application/json");
		 Response response = request.post("/jsonBlob");
		 
		  statusCode = response.getStatusCode();
		  
		  BLobID = response.headers().getValue("X-jsonblob");
		  contentType = response.headers().getValue("Content-Type");
		}
	
	@Given("user will put the resuest as")
	public void user_will_put_the_resuest_as(io.cucumber.datatable.DataTable dataTable) {
		 RestAssured.baseURI ="https://jsonblob.com/api/jsonBlob";
		 RequestSpecification request = RestAssured.given();
		 
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("people",dataTable); // Cast
		 request.body(requestParams.toString());
		 request.header("Accept","application/json");
		 request.header("Content-Type","application/json");
		 Response response = request.put("/"+BLobID);
		 
		 statusCode = response.getStatusCode();
		  
		  BLobID = response.headers().getValue("X-jsonblob");
		  contentType = response.headers().getValue("Content-Type");
		  ResponseBody = response.body().asString();
	}
	
	@Then("user will verify the profile details of the user been updated to {string}")
	public void user_will_verify_the_profile_details_of_the_user_been_updated_to(String string) {
		Assert.assertTrue(ResponseBody.contains(string));
	}
	@Given("user will delete the blobid that has been added")
	public void user_will_delete_the_blobid_that_has_been_added() {
		RestAssured.baseURI = "https://jsonblob.com/api/jsonBlob";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.DELETE, "/"+BLobID);
	    statusCode = response.getStatusCode();
	    
	}
	@Then("verify user profile has been deleted")
	public void verify_user_profile_has_been_deleted() {
		RestAssured.baseURI ="https://jsonblob.com/api/jsonBlob";
		 RequestSpecification request = RestAssured.given();
		 
		 JSONObject requestParams = new JSONObject();
		 request.body(requestParams.toString());
		 request.header("Accept","application/json");
		 request.header("Content-Type","application/json");
		 Response response = request.get("/"+BLobID);
		 
		 statusCode = response.getStatusCode();
		  BLobID = response.headers().getValue("X-jsonblob");
		  contentType = response.headers().getValue("Content-Type");
		  ResponseBody = response.body().asString();
		  Assert.assertTrue(response.headers().getValue("Content-Length").equals("0"));
	}

	
	@Then("user will verify the profile details of the user with username as {string}")
	public void user_will_verify_the_profile_details_of_the_user_with_username_as(String userName) {
		Assert.assertEquals("The profile name doesn't matched", profileName, userName);
	}
	
	





@Then("user will verify the response code as {string}")
public void user_will_verify_the_response_code_as(String responseCode) {
	System.out.println("Response Code is =>  " + statusCode);
	Assert.assertEquals(statusCode.toString(), responseCode);
}

@Then("Content type as {string}")
public void content_type_as(String string) {
	System.out.println("Response Code is =>  " + statusCode);
	Assert.assertEquals(contentType.toString(), string);
}

}
