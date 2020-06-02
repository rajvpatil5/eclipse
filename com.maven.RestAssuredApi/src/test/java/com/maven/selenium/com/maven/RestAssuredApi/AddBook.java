package com.maven.selenium.com.maven.RestAssuredApi;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.Test;

import DataproviderApi.AddBookDataprovider;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class AddBook {
	public static String bookid;

	@Test(dataProvider = "BooksDataprovider", dataProviderClass = AddBookDataprovider.class)
	public void addBook(String isbn, String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(Payload.addBook(isbn, aisle)).when().post("Library/Addbook.php").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();
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
/*
 * adffdfre213212 wertrr13213 tyytrtyr535
 */