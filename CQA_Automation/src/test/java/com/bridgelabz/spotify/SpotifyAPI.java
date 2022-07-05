package com.bridgelabz.spotify;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpotifyAPI {
	String token;
	String user_id = "31j235tqsa7trlqlr3cjkqzwhvnm";
	String playlist_id ="4pfgW3SrAGapctdlsu6Qat";
	String track_id;

	
	@BeforeTest
	public void getToken()
	   {
		 token = "Bearer BQCC6UhkBRX-IkNC81Z8nzbj1ZUuuTzWi8AgCFZtIFh8M9Fu2xaz7OOEG1pWRsYAI5UHTRRnnU7zgWuJmsiu4XQSGLm-RaNLProKyZn0xrQORxUB724wQ4pklk0QIhthmxyZXABbaHI_uboljFPVE8nvwBVKQuL5WPSTv70CU2_X6ZdJlQL53AFW57c0-0RSYYanuN-uMoC7wCsYaK0F9gcxrcacCdT7GlGtTSt5N5VlOcNWFSBl6TO65gBigwuAdPDgQF1pJC3hXCkrTw2fb3lLe9J0Pw_K3viHrwczvpyTIEGqTFsvSH_HlA_7UA";
		 track_id = "5X5mUYJ9TUwosuEAZFzKEX";
	   }
	
	// Current Users Profile	
	
	@Test (priority = 1)
	public void Get_Current_Users_Profile()
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/me");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	

	//  User Profile
	
	@Test (priority = 2)
	public void Get_Users_Profile() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+user_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	
	
	// Post Create Playlist
	
	@Test (priority = 3)
	public void Create_Playlist()
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"Rest_assured Api\",\r\n"
						+ "  \"description\": \"New playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.post("https://api.spotify.com/v1/users/"+user_id+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
       }
	
	
	
	//  Post Add Items to Playlist
	
		@Test (priority = 4)
		public void Add_Items_to_Playlist() 
		   {
			Response response = RestAssured.given().contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Authorization", token)
					.queryParams("uris","spotify:track:5X5mUYJ9TUwosuEAZFzKEX,spotify:track:6cZER0XaxSRdwBxebDINsk,spotify:track:5pjSpt2mstf5JTf46FbT48,spotify:track:1OeX1bIG5kIHoPDnaNnSx5" )
					.when()
					.post("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
			response.prettyPrint();
			response.then().assertThat().statusCode(201);
		   }
		
		
		
		//  Put Update Playlist Items
		
		@Test (priority = 5)
		public void Update_Playlist_Items()
		   {
			Response response = RestAssured.given().contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Authorization", token)
					.body("{\r\n"
							+ "  \"range_start\": 1,\r\n"
							+ "  \"insert_before\": 0,\r\n"
							+ "  \"range_length\": 3\r\n"
							+ "}")
					.when()
					.put("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
			response.prettyPrint();
			response.then().assertThat().statusCode(200);
		   }
		
		
		
		//  Put Change Playlist Details
		
		@Test (priority = 6)
		public void Change_Playlist_Details()
		   {
			Response response = RestAssured.given().contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Authorization", token)
					.body("{\r\n"
							+ "  \"name\": \"Automation api\",\r\n"
							+ "  \"description\": \"Updated playlist description\",\r\n"
							+ "  \"public\": false\r\n"
							+ "}")				
					.when()
					.put("https://api.spotify.com/v1/playlists/"+playlist_id+"");
			response.prettyPrint();
			response.then().assertThat().statusCode(200);
		   }
		
		
	
		
	// Get Current User Playlist
		
	@Test (priority = 7)
	public void Get_Current_User_Playlists() 
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	
	
	//  Get User Playlists
	
	@Test (priority = 8)
	public void Get_User_Playlists() 
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+user_id+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	
	
	//  Get Playlist Cover Image
	
	@Test (priority = 9)
	public void Get_Playlist_Cover_Image()
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/images");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	
	
	// Get Items from Playlist
	
	@Test (priority = 10)
	public void Get_Playlist_Items()
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	
	
	//  Get Playlist
	
	@Test (priority = 11)
	public void Get_Playlist() 
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlist_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	
	
	//  Delete Remove Playlist Items
	
	@Test (priority = 12)
	public void Remove_Playlist_Items()
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{ \"tracks\": [{ \"uri\": \"spotify:track:3hkC9EHFZNQPXrtl8WPHnX\" }]} ")
				//.queryParam("uris"," spotify:track:5O932cZmzOZGOGZz9RHx20")
				.when()
				.delete("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	
	
	//  Get Search for Item
	
	@Test (priority = 13)
	public void Search_for_Item() 
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("q","Arijit singh")
				.queryParam("type","track")
				.when()
				.get("https://api.spotify.com/v1/search");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	
	
	//  Get Track Audio Analysis
	
	@Test (priority = 14)
	public void Get_Track_Audio_Analysis() 
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-analysis/"+track_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	
	
	// Get Track Audio Features
	
	@Test (priority = 15)
	public void Get_Tracks_Audio_Features()
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	  }
	
	
	
	//  Get Track Audio Features
	
	@Test (priority = 16)
	public void Get_Track_Audio_Features() 
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/"+track_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	
	
	// Get Several Tracks
	
	@Test (priority = 17)
	public void Get_Several_Tracks() 
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.param("ids", track_id)
				.when()
				.get("https://api.spotify.com/v1/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	   }
	
	
	
	// Get Track
	
	@Test (priority = 18)
	public void Get_Track()
	   {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/tracks/"+track_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
       }
} 