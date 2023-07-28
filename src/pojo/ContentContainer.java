package pojo;

import java.util.List;

public class ContentContainer {
	private List<Content> content;
	private String type;
	
	// Constructor
	public ContentContainer() {
		
	}
	
	public ContentContainer(List <Content>  contents) {
		this.content = contents;
	}
	
	public ContentContainer(List <Content> contents, String type) {
		this.content = contents;
		this.type = type;
	}
	
	// Getters and Setters
	public List <Content> getContent() {
		return content;
	}
	public void setContent(List <Content> content) {
		this.content = content;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
