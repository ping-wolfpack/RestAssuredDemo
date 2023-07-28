
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import files.payload;
import files.payloadJira;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import pojo.Body;
import pojo.Content;
import pojo.ContentContainer;
import pojo.IssueCommentAdd;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class JiraAPI extends Base {
	
	int issueKey=0;
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response resp;
	
	
	@BeforeClass
    public void setup() {
		//RestAssured.baseURI="https://emailping.atlassian.net";
		reqSpec = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri("https://emailping.atlassian.net")	
				.build();
		reqSpec.header("Authorization", "Basic "+getAuthToken());
		resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
    }
	
	@AfterMethod
	public void print() {
		System.out.println(resp.asPrettyString());
	}
	
	@Test(priority = 1)
	public void JiraIssueCreate() {
		resp =
			given()
				.spec(reqSpec)
				.body(payloadJira.AddIssue())
			.when()
				.post("rest/api/3/issue/")
			.then()		
				.spec(resSpec)
				.assertThat().statusCode(201)
				.extract().response();
		
		issueKey = resp.jsonPath().getInt("id");
	        
	    System.out.println("======== Issue Create ============");
	
	}
	
	@Test(priority = 2)
	public void JiraIssueUpdate() {
		resp =
			given()
				.spec(reqSpec)
				//.log().all()
				.body(payloadJira.UpdateIssue())
			.when()
				.put("rest/api/3/issue/"+issueKey)
			.then()					
				.assertThat().statusCode(204)
				.extract().response();
		System.out.println("======== Issue Update ============");
		
	}
	
	@Test(priority = 5)
	public void JiraIssueGet() throws JsonMappingException, JsonProcessingException {
		
		resp =
			given()
				.spec(reqSpec)
				.pathParam("issueKey", issueKey)
				.queryParam("fields", "comment")
			.when()
				.get("rest/api/3/issue/{issueKey}")
			.then()					
				.assertThat().statusCode(200)
				.extract().response();
		
		System.out.println("========= Issue Get ===========");
		
		/*
	    System.out.println("========= Fields / comment ===========");       
        int size = resp.jsonPath().getInt("fields.comment.comments.size()");
        for (int i = 0 ; i<size; i++) {
        	System.out.println(resp.jsonPath().getString("fields.comment.comments["+i+"].body.content[0].content[0].text"));
        }
        
        JsonPath message = new JsonPath(payloadJira.AddComment());
        System.out.println(message.getString("body.content[0].content[0].text"));
        
        Assert.assertEquals();
        */
		
		/*
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		IssueCommentAdd commentFromJson = objectMapper.readValue("", IssueCommentAdd.class);
		System.out.println(commentFromJson.getBody().getContent().get(0).getContent().get(0).getText());
		*/
	}
	
	@Test(dependsOnMethods = "JiraIssueGet")
	public void JiraIssueDelete() {
		resp =
			given()
				.spec(reqSpec)					
			.when()
				.delete("rest/api/3/issue/"+issueKey)
			.then()					
				.assertThat().statusCode(204)
				.extract().response();
		System.out.println("========= Issue Delete ===========");
		
	}
	
	
	
	@Test(priority = 3)
	public void JiraIssueCommentCreate() {	
		
        resp =
			given()
				.spec(reqSpec)			
				.pathParam("issueKey",issueKey)
				.body(payloadJira.AddComment())
			.when()
				.post("rest/api/3/issue/{issueKey}/comment")
			.then()
				.assertThat().statusCode(201)
				.extract().response();
        
        System.out.println("========= Issue Comment Create ===========");
        
        
	}
	
	@Test(priority = 4)
	public void JiraIssueCommentCreateUsingPojo() throws JsonProcessingException {
		Content content = new Content("text", "comment text 1");
		
		List <Content> contentList = new ArrayList<>();
		contentList.add(content);
		
		
		ContentContainer innerContent = new ContentContainer(contentList, "paragraph");
		
		List <ContentContainer> outerContent = new ArrayList<>();
		outerContent.add(innerContent);
		
		Body b = new Body(outerContent, "doc", 1);
		
		IssueCommentAdd comment = new IssueCommentAdd(b);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(comment);
	
		
		resp =
				given()
					.spec(reqSpec)
					.pathParam("issueKey",issueKey)
					.body(jsonString)
				.when()
					.post("rest/api/3/issue/{issueKey}/comment")
				.then()
					.assertThat().statusCode(201)
					.extract().response();
	        
	        System.out.println("========= Issue Comment Create (POJO) ===========");
	        
	
	}
	
	public void apitest1() {
		RestAssured.baseURI="http://google.com";
		
		Response resp = 
		given()
		.when()
			.get("/")
		.then()
			.log().all()
			.assertThat().statusCode(200)
			.extract().response();
		
		System.out.println(resp.jsonPath().getString("id"));	
		
	}
	
}
