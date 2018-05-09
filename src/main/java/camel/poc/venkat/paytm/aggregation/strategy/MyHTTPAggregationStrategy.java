package camel.poc.venkat.paytm.aggregation.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.json.JSONArray;
import org.json.JSONObject;

public class MyHTTPAggregationStrategy implements AggregationStrategy{

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		// TODO Auto-generated method stub
		JSONObject newdata=newExchange.getIn().getBody(JSONObject.class);
		JSONObject oldExchangeData;
		if(oldExchange==null) {
			oldExchange=newExchange;
			oldExchangeData=new JSONObject();
		}else {
			oldExchangeData=oldExchange.getIn().getBody(JSONObject.class);
		}
		if(newdata.has("isUserInfo")&&newdata.getBoolean("isUserInfo")) {
			oldExchangeData.put("userInfo", newdata);
		}
		
		if(newdata.has("isReposInfo")&&newdata.getBoolean("isReposInfo")) {
			oldExchangeData.put("repoInfo", newdata);
		}
		oldExchange.getIn().setBody(oldExchangeData);
		return oldExchange;
	}

}
