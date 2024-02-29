package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		PlaceValidation p =new PlaceValidation();
		if(PlaceValidation.placeId == null)
		{
			p.add_place_payload_with("Rahul", "Tamil", "Bengaluru");
			p.user_calls_with_http_request("addPlaceAPI", "Post");
			p.verify_place_id_created_maps_to_using("Rahul", "getPlaceAPI");
			System.out.println("Start Gitstuff");
			System.out.println("added new develop branch and start done some changes");
			System.out.println("working on new develop branch by git stuff");
			System.out.println("commiting from master through git trial");
		}
	}
}
