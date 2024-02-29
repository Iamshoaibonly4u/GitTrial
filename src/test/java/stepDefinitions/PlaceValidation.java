package stepDefinitions;

import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;

public class PlaceValidation extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild test = new TestDataBuild();
	static String placeId;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    res = given().spec(resquestSpecification()).body(test.addPlacePayload(name,language,address));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method)  {
		
		//constructor will be called with value of resource which you pass 
		APIResources resourcesAPI = APIResources.valueOf(resource);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
			response = res.when().post(resourcesAPI.getResource());
		else if(method.equalsIgnoreCase("Get"))
			response = res.when().get(resourcesAPI.getResource());
      			
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer code) {
		response.then().spec(resSpec).extract().response();
		assertEquals(response.getStatusCode(), 200);
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	  
	   assertEquals(getJsonPath(response,keyValue),expectedValue);
	}
	@Then("verify placeId created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    
	    placeId = getJsonPath(response, "place_id");
		res = given().spec(resquestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		System.out.println(placeId+" delete");
		res = given().spec(resquestSpecification()).body(test.deletePlacePayload(placeId));
	}
}
