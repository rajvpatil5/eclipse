package com.maven.selenium.com.maven.RestAssuredApi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

public class DemoDeleteBooks {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://216.10.245.166";
		given().log().all().header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"ID\": \"tyytrtyr535\"\r\n" + 
				"}\r\n" + 
				"").
		when().delete("/Library/DeleteBook.php")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("book is successfully deleted"));
		
	}

}
