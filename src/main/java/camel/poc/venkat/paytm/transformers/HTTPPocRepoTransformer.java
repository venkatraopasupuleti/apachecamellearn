package camel.poc.venkat.paytm.transformers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class HTTPPocRepoTransformer {
	public JSONObject truncatedata(String reposString) {
		JSONArray reposInfoJSONArray=new JSONArray(reposString);
		JSONObject truncatedJSONObject=new JSONObject();
		truncatedJSONObject.put("isReposInfo", true);
		List<JSONObject> jsonlist=new ArrayList<JSONObject>();
		for(int i=0;i<reposInfoJSONArray.length();i++) {
			JSONObject userInfoJSON=reposInfoJSONArray.getJSONObject(i);
			JSONObject truncatedJSON=new JSONObject();
			truncatedJSON.put("id", userInfoJSON.getInt("id"));
			truncatedJSON.put("name", userInfoJSON.getString("name"));
			jsonlist.add(truncatedJSON);
			
		}
		truncatedJSONObject.put("repos", jsonlist);
		return truncatedJSONObject;
	}
}
