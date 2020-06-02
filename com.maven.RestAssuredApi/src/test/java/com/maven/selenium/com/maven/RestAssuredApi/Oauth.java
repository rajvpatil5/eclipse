package com.maven.selenium.com.maven.RestAssuredApi;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Pojo.GetCourses;

public class Oauth {

	public static void main(String[] args) {
		/*
		 * https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com
		 * /auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&
		 * client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.
		 * googleusercontent.com&response_type=code&redirect_uri=https://
		 * rahulshettyacademy.com/getCourse.php
		 */

		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AF6rYLojBktLfPDLq-s9vUSc49YX8ynV2WmDZIz2Z5DlUfvsRFSR6qva_JyMcHy2jQfrT5Za3rTvgEb4QHLgNs&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		String code = url.split("code=")[1].split("&scope")[0];
		System.out.println(code);
		String accessTokenResponse = given().urlEncodingEnabled(false).queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("grant_type", "authorization_code")
				.queryParam("state", "verifyfjdss")
				.queryParam("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
		System.out.println(accessTokenResponse);

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		String response = given().contentType("application/json").queryParams("access_token", accessToken).expect()
				.defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);

		GetCourses getcourse = given().contentType("application/json").queryParams("access_token", accessToken).expect()
				.defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php")
				.as(GetCourses.class);
		System.out.println("========================");
		System.out.println(getcourse.getLinkedIn());
		System.out.println(getcourse.getCourses().getWebAutomation().get(1).getCourseTitle());
	}

}
