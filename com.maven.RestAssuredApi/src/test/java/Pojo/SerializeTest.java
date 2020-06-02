package Pojo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;

public class SerializeTest {
	@Test
	public void test() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		AddPlace place = new AddPlace();
		Location location = new Location();
		location.setLat(33.654887);
		location.setLng(85.546546);
		place.setAccuracy(50);
		place.setAddress("Gajanan nagar, Arvi");
		place.setLanguage("marathi");
		place.setLocation(location);
		place.setName("Rajat");
		place.setPhoneNumber("8149915224");
		ArrayList<String> types = new ArrayList<String>();
		types.add("shoe-park");
		types.add("shopes");
		place.setTypes(types);
		place.setWebsite("helloworld.com");
		String response = given().log().all().queryParam("key", "qaclick123").body(place).when()
				.post("maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println("===========");
		System.out.println(response);

	}
}
