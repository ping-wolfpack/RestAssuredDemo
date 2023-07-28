import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;

import files.payload;

public class Basics {

	public static void main(String[] args) {
		// AddPlace API
		// given - all input detail
		// when  - submit the API - resource, http method
		// then  - validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String resp = 
		given()
			.log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(payload.AddPlaceData())
		.when()
			.post("/maps/api/place/add/json")
		.then()
			.log().all()	
			.assertThat()
				.statusCode(200)
				.header("server", "Apache/2.4.52 (Ubuntu)")
				.body("scope",equalTo("APP")) //equalTo from hemcrest library
			.extract().response().asString();
		
		System.out.println("========");
		System.out.println(resp);
		
		JsonPath respJson = new JsonPath(resp);
		String placeId = respJson.getString("place_id");
		System.out.println(placeId);
		
		
		// UpdatePlace API
		String newAddress = "Summer Walk, Africa";
		
		given()
			.log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(payload.UpdatePlaceData(placeId, newAddress))
		.when()
			.put("/maps/api/place/update/json")
		.then()
			.log().all()
			.assertThat()
				.statusCode(200)
				.body("msg",equalTo("Address successfully updated"));
		
		// GetPlace API
		
		String getResp =
		given()
			.log().all()
			.queryParam("key", "qaclick123")
			.queryParam("place_id", placeId)
		.when()
			.get("/maps/api/place/get/json")
		.then()
			.log().all()
			.assertThat()
				.statusCode(200)
			.extract().response().asString();
		
		JsonPath getRespJson = new JsonPath(getResp);
		String address = getRespJson.getString("address");
		
		Assert.assertEquals(address, newAddress);
					
		
		// AddPlace > UpdatePlace > GetPlace
		
	}

}
