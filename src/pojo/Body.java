package pojo;

import java.util.List;

public class Body {
	private List <ContentContainer> content;
	private String type;
	private int version;
	
	public Body() {
		
	}
	
	public Body(List <ContentContainer> content, String type, int version) {
		this.content = content;
		this.type = type;
		this.version = version;
		
	}
	
	public List <ContentContainer> getContent() {
		return content;
	}
	public void setContent(List <ContentContainer> content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	

}
