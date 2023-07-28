import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import files.payload;
import io.restassured.path.json.JsonPath;
import pojo.Body;
import pojo.Content;
import pojo.ContentContainer;
import pojo.IssueCommentAdd;


public class JsonTest {

	public static void main(String[] args) throws JsonProcessingException {
		// TODO Auto-generated method stub
		
		
		JsonPath respJson = new JsonPath(payload.SampleJson());
		
		System.out.println(payload.SampleJson());
		
		
		// Print # of courses returned by API
		int size = respJson.getInt("courses.size()");
		System.out.println(size);
		
		
		// Print Purchase Amount
		int total = respJson.getInt("dashboard.purchaseAmount");
		System.out.println(total);
		
		// Print Title of the first course
		System.out.println(respJson.getString("courses[0].title"));

		// Print all courses titles and their respective prices
		for(int i =0; i<size;i++) {
			System.out.print(respJson.getString("courses[" + i + "].title") + " \t");
			System.out.println(respJson.getString("courses[" + i + "].price"));
		}
		
		// Print # of copies sold by java course
		for(int i =0; i<size;i++) {
			String courseTitle = respJson.getString("courses[" + i + "].title");
			if( courseTitle.equalsIgnoreCase("java")) {
				System.out.println(respJson.getString("courses["+i+"].copies"));
				break;
			}
			
		}
		
		
	
		
		Content content = new Content("doc", "comment text 1");
	
		List <Content> contentList = new ArrayList<>();
		contentList.add(content);
		
		
		ContentContainer innerContent = new ContentContainer(contentList, "paragraph");
		
		List <ContentContainer> outerContent = new ArrayList<>();
		outerContent.add(innerContent);
		
		Body b = new Body(outerContent, "doc", 1);
		
		IssueCommentAdd comment = new IssueCommentAdd(b);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(comment);
		
		
		System.out.println(jsonResult);
		
	}
	
	@Test
	public void SumValidation() {
		// Validate the total match up
		JsonPath respJson = new JsonPath(payload.SampleJson());
		int size = respJson.getInt("courses.size()");
		int total = 0;
		for (int i = 0; i < size; i++) {
			int price = respJson.getInt("courses["+i+"].price");
			int copies = respJson.getInt("courses["+i+"].copies");
			total += (price*copies);
		}
		int purchaseAmount = respJson.getInt("dashboard.purchaseAmount");
		System.out.println(total);
		assertEquals(total, purchaseAmount);
		
	}

}
