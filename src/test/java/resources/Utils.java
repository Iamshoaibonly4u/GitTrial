package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	
	public RequestSpecification resquestSpecification() throws IOException {
		
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
	     req =  new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("key", "qaclick123")
	    		.addFilter(RequestLoggingFilter.logRequestTo(log))
	    		.addFilter(ResponseLoggingFilter.logResponseTo(log))
	    .setContentType(ContentType.JSON).build();
	     return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		FileInputStream fis = new FileInputStream("src/test/java/resources/Global.properties");
		Properties p = new Properties();
		p.load(fis);
		return p.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
