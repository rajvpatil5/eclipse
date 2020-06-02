package com.maven.selenium.com.maven.RestAssuredApi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Payload {

	public static String addPlace() {
		return "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
				+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" + "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n" + "  ],\r\n" + "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n" + "}\r\n" + "";
	}

	public static String addBook(String isbn, String aisle) {
		return "{\r\n" + "\r\n" + "\"name\":\"Learn Appium with Java\",\r\n" + "\"isbn\":\"" + isbn + "\",\r\n"
				+ "\"aisle\":\"" + aisle + "\",\r\n" + "\"author\":\"John foe\"\r\n" + "}\r\n" + " \r\n" + "";
	}

	public static String loadStaticJson(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
