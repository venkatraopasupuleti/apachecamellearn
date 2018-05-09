package camel.poc.venkat.paytm.aggregation.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.json.JSONArray;
import org.json.JSONObject;



public class MyFileAggregationStrategy implements AggregationStrategy {

	public Exchange aggregate(Exchange arg0, Exchange arg1) {
		// TODO Auto-generated method stub
		Exchange myexchange;
		JSONArray olddata;
		if(arg0==null) {
			//first message
			myexchange=arg1;
			olddata=new JSONArray();
		}else {
			myexchange=arg0;
			olddata=myexchange.getIn().getBody(JSONArray.class);
		}
		JSONObject client2data=arg1.getIn().getBody(JSONObject.class);
		olddata.put(client2data);
		myexchange.getIn().setBody(olddata);
		return myexchange;
	}

}
