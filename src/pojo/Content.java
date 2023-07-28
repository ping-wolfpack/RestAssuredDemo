package pojo;

public class Content {
	private String text;
	private String type;
	
	public Content() {
		
	}
	
	public Content(String type) {
		this.type = type;
	}
	
	public Content(String type, String text) {
		this.type = type;
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
