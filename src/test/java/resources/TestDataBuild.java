package resources;

import java.util.ArrayList;
import java.util.List;

import pojoClasses.GoogleAPIBody;
import pojoClasses.Location;

public class TestDataBuild {

	public GoogleAPIBody addPlacePayload(String name, String language, String address) {
		GoogleAPIBody data = new GoogleAPIBody();
	    Location l = new Location();
	    l.setLat(-38.383494);
	    l.setLng(33.427362);
	    data.setLocation(l);
	    data.setAccuracy(50);
	    data.setName(name);
	    data.setPhone_number("(+91) 983 893 3937");
	    data.setAddress(address);
	    List<String> list = new ArrayList<>();
	    list.add("shoe park");
	    list.add("shop");
	    data.setTypes(list);
	    data.setWebsite("http://google.com");
	    data.setLanguage(language);
	    return data;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
