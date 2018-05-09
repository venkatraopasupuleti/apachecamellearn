package camel.poc.venkat.paytm.aggregation.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.json.JSONObject;

import com.google.gson.JsonArray;

public class HTTPCompletionStrategy implements Predicate {

	public boolean matches(Exchange exchange) {
		// TODO Auto-generated method stub
		if(exchange.getIn().getBody()!=null&&exchange.getIn().getBody(JSONObject.class).has("userInfo")&&exchange.getIn().getBody(JSONObject.class).has("repoInfo")) {
			return true;
		}
		return false;
	}

}
