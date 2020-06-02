package com.maven.selenium.com.maven.RestAssuredApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJson {
	public static String bookid;

	@Test()
	public void addBook() throws IOException {

		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(Payload.loadStaticJson("D:\\API Testing\\AddBook.json")).when().post("Library/Addbook.php").then()
				.log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(response);
		bookid = js.get("ID");
		System.out.println(bookid + "======================");
	}

	@AfterMethod
	public void deleteBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		// delete Book
		given().log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"ID\": \"" + bookid + "\"\r\n" + "}\r\n" + "").when()
				.delete("/Library/DeleteBook.php").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("book is successfully deleted"));
	}
}
