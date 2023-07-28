package files;

public class payloadJira {
	public static String AddIssue() {
		return "{\n"
				+ "    \"fields\": {\n"
				+ "        \"project\":{\n"
				+ "            \"key\":\"DP\"\n"
				+ "        },\n"
				+ "        \"summary\": \"Main order flow broken\",\n"
				+ "        \n"
				+ "        \"issuetype\":{\n"
				+ "            \"name\": \"Task\"\n"
				+ "        }\n"
				+ "    }\n"
				+ "    \n"
				+ "}";
	}
	
	public static String UpdateIssue() {
		return "{\n"
				+ "    \"update\": {\n"
				+ "        \"summary\": [\n"
				+ "            {\n"
				+ "                \"set\": \"Bug in business logic\"\n"
				+ "            }\n"
				+ "        ]\n"
				+ "    }\n"
				+ "}";
	}
	
	public static String AddComment() {
		return "{\n"
				+ "    \"body\": {\n"
				+ "        \"content\": [\n"
				+ "            {\n"
				+ "                \"content\": [\n"
				+ "                    {\n"
				+ "                        \"text\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.\",\n"
				+ "                        \"type\": \"text\"\n"
				+ "                    }\n"
				+ "                ],\n"
				+ "                \"type\": \"paragraph\"\n"
				+ "            }\n"
				+ "        ],\n"
				+ "        \"type\": \"doc\",\n"
				+ "        \"version\": 1\n"
				+ "    }\n"
				+ "}";
	}
}




