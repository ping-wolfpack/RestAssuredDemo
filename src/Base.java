
public class Base {
	private String jiraUsername = "emailping@gmail.com";
    private String jiraApiToken = "ATATT3xFfGF0-Sb0msic16bVTY49ahdB8PlZ3FQqlphxPp-QjiKo8RnQobhcPRFNDVZjq_GyQEFie3FylFSZs-7yl35-_h1epHP09TU6O_KuBq-ZAhq0f3W5XtDg8IgAPQiSQ2kk8b8dRMaTSbIbP2Y1p9iqIfQlzazytIWhXjuumnoxVhI95ts=51335B2C";
    private String base64Credentials = 
    		java.util.Base64
    			.getEncoder()
    			.encodeToString((jiraUsername + ":" + jiraApiToken).getBytes());

    public String getAuthToken() {
    	return base64Credentials;
    }
    
}
