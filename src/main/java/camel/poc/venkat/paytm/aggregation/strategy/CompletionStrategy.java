package camel.poc.venkat.paytm.aggregation.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.json.JSONArray;

public class CompletionStrategy implements Predicate{

	public boolean matches(Exchange arg0) {
		// TODO Auto-generated method stub
		JSONArray data=arg0.getIn().getBody(JSONArray.class);
		if(data!=null&&data.length()==2) {
			return true;
		}
		return false;
	}

}
