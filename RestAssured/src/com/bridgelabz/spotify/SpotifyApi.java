package com.bridgelabz.spotify;

import org.tstng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class SpotifyApi {
	public String token;
	String userId;
	String playlistId;
	
	@BeforeTest
	public void getToken() {
		token =" Bearer BQAL0MoHCfQi9jeBfH0JE6J88j4aDtHF_gpqbojogCQjWCDQT9V1ShWVpYTwzvbBPvyPCrbfGUqwMme4AYuzuE9iH4OASaKHzI18ONYBFtXH78goOb4nUVfnih--Zblb2JluchgDPpCJsheL5S7rnCATk8AInxLplE0q3Hy8yrt1DoWs6LlpxA1gIR0DTss3rYdkKEZpFm0yUUNPjAtwqc9rpNc13H9fsPB8lePhLj0eKZkRPvZ4Bcbn1gjeJvY7hz2OiTxTZmQFv4lv-oNtcKiGGOqL_Dk5kNAduc_X";
	}
	
	@Test (priority = 0)
	public void testGet_Current_Profile() {
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.get(" 	https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
		userId = response.path("id");
		System.out.println("USer Id is "+userId);
		response.then().assertThat().statusCode(200);

		Assert.assertEquals(userId, "317v5jucqs74swivw6377z2k6f5q");
	}
	
	@Test (priority = 1)
	public void testGet_UserProfile() {
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/users/"+userId+"");
		response.prettyPrint();
		int statusCode = response.getStatusCode();
		System.out.println("status code " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test (priority = 2)
	public void createPlaylist() {
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization",token)
				.body("{\r\n"
						+ "  \"name\": \"Automation t\",\r\n"
						+ "  \"description\": \"New playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.post("https://api.spotify.com/v1/users/"+userId+"/playlists");
		response.prettyPrint();
		playlistId = response.path("id");
		System.out.println("Playlist Id is "+playlistId);
		response.then().assertThat().statusCode(201);
	}
}