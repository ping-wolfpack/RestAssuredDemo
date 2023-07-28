package files;

public class payload {
	public static String AddPlaceData() {
		return "{\n"
				+ "    \"location\": {\n"
				+ "        \"lat\": -38.383494,\n"
				+ "        \"lng\": 33.427362\n"
				+ "    },\n"
				+ "    \"accuracy\": 50,\n"
				+ "    \"name\": \"Frontline house\",\n"
				+ "    \"phone_number\": \"(+91) 983 893 3937\",\n"
				+ "    \"address\": \"29, side layout, cohen 09\",\n"
				+ "    \"types\": [\n"
				+ "        \"shoe park\",\n"
				+ "        \"shop\"\n"
				+ "    ],\n"
				+ "    \"website\": \"http://google.com\",\n"
				+ "    \"language\": \"French-IN\"\n"
				+ "}";
	}
	
	public static String UpdatePlaceData(String id, String address) {
		return "{\n"
				+ "    \"place_id\": \""+id+"\",\n"
				+ "    \"address\": \""+address+"\",\n"
				+ "    \"key\": \"qaclick123\"\n"
				+ "}";
	}
	
	
	public static String SampleJson() {
		return "{\n"
				+ "	\"dashboard\": {\n"
				+ "		\"purchaseAmount\" : 910,\n"
				+ "		\"website\": \"rahulshettyacademy.com\"\n"
				+ "	},\n"
				+ "	\n"
				+ "	\"courses\": [\n"
				+ "		{\n"
				+ "			\"title\":\"selenium\",\n"
				+ "			\"price\":50,\n"
				+ "			\"copies\":6\n"
				+ "		},\n"
				+ "		\n"
				+ "		{\n"
				+ "			\"title\":\"cypress\",\n"
				+ "			\"price\":40,\n"
				+ "			\"copies\":4\n"
				+ "		},\n"
				+ "		\n"
				+ "		{\n"
				+ "			\"title\":\"java\",\n"
				+ "			\"price\":45,\n"
				+ "			\"copies\":10\n"
				+ "		}\n"
				+ "	\n"
				+ "	\n"
				+ "	]\n"
				+ "}";
	}
	
	public static String AddBookData(String isbn, String aisle) {
		return "{\n"
				+ "	\"name\": \"Learn appim automation with java\",\n"
				+ "	\"isbn\": \""+isbn+"\",\n"
				+ "	\"aisle\": \""+aisle+"\",\n"
				+ "	\"author\": \"John foe\"\n"
				+ "}";
	}
	

}
