import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.testng.Assert;

import files.payload;


import io.restassured.RestAssured;

public class DynamicJson {
	
	@Test(dataProvider="bookdata")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		
		String resp =
		given()
			.contentType("application/json")
			.body(payload.AddBookData(isbn, aisle))
		.when()
			.post("/Library/Addbook.php")
		.then()
			.log().all()
			.assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath jsonResp = new JsonPath(resp);
		
		String id = jsonResp.getString("ID");
		System.out.println(id);
		
	}
	
	@DataProvider(name="bookdata")
	public Object[][] getData() {
		return new Object[][] {{"1111","aaaa"},{"2222","bbbb"},{"3333","cccc"}};
	}
	
	@Test
	public void addBookByJsonFile() throws IOException {
		RestAssured.baseURI="http://216.10.245.166";
		
		//String filePath = System.getProperty("user.dir")+"/src/files/bookdata.json";
		//String jsonBody = new String(Files.readAllBytes(Paths.get(filePath)));
		
		File jsonFile = new File(System.getProperty("user.dir")+"/src/files/bookdata.json");
	    
		
		String resp =
		given()
			.contentType("application/json")
			.body(jsonFile)
		.when()
			.post("/Library/Addbook.php")
		.then()
			.log().all()
			.assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath jsonResp = new JsonPath(resp);
		
		String id = jsonResp.getString("ID");
		System.out.println(id);
		
		
	
		
	}

}
