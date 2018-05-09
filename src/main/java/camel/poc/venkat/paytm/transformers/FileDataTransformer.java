package camel.poc.venkat.paytm.transformers;

import org.json.JSONObject;

public class FileDataTransformer {
	
	public JSONObject convertToJSON(String filedata) {
		JSONObject filejson;
		try {
			filejson=new JSONObject(filedata);
		}catch(Exception e){
			//adding default values
			filejson=new JSONObject();
			filejson.put("name", "defaultname");
			filejson.put("employeeid", "defaultemployeeid");
		}
		return filejson;
	};

}
