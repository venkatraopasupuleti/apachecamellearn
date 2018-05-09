package camel.poc.venkat.paytm.transformers;

import org.json.JSONObject;

public class HTTPPocUserTransformer{
	
	public JSONObject truncatedata(String userInfoString) {
		JSONObject userInfoJSON=new JSONObject(userInfoString);
		
		JSONObject truncatedJSON=new JSONObject();
		truncatedJSON.put("name", userInfoJSON.getString("name"));
		truncatedJSON.put("location", userInfoJSON.getString("location"));
		truncatedJSON.put("bio", userInfoJSON.getString("bio"));
		truncatedJSON.put("bio", userInfoJSON.getString("bio"));
		truncatedJSON.put("isUserInfo", true);
		return truncatedJSON;
	}
}
