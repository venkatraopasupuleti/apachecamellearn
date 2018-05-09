package camel.poc.venkat.paytm.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;

public class FileDataProcessor implements Processor {
	
	public void process(Exchange arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(arg0.getIn().getBody().toString());
		
	}

}
