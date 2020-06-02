import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class PostGet {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response()
				.asString();
		System.out.println(response);
		JsonPath js2 = new JsonPath(response);
		String place = js2.getString("place_id");
		System.out.println(place);

		// Get place
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", "ad4cb1db9cce493da066d4cb9ac43b61").when().get("maps/api/place/get/json").then()
				.assertThat().log().all().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(getPlaceResponse);
		String address = js.getString("address");
		System.out.println(address);

		// updatePlace
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + place + "\",\r\n" + "\"address\":\"70 Summer walk, India\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get place
		String getPlaceResponse1 = given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", "" + place + "").when().get("maps/api/place/get/json").then().assertThat().log()
				.all().statusCode(200).extract().response().asString();
		JsonPath js1 = new JsonPath(getPlaceResponse1);
		String address1 = js1.getString("address");
		System.out.println(address1);

	}

}
