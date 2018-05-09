package camel.poc.venkat.paytm.consumers;

//public class HTTPConsumer {
//
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import camel.poc.venkat.paytm.endpoints.MyHTTPEndPoint;

public class HTTPConsumer extends ScheduledPollConsumer {
	
	private MyHTTPEndPoint endpoint;

	public HTTPConsumer(MyHTTPEndPoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.setDelay(endpoint.getConfiguration().getDelay());
	}

	@Override
	protected int poll() throws Exception {
		
		String operationPath = endpoint.getOperationPath();
		
		return processSearchFeeds(operationPath);
		
		// only one operation implemented for now !
		//throw new IllegalArgumentException("Incorrect operation: " + operationPath);
	}
	
	private String performGetRequest(String uri) throws ClientProtocolException, IOException {
		
		HttpClient client = HttpClientBuilder.create().build();			
		HttpGet request = new HttpGet("https://api.github.com/" + uri+"?client_id=7b9a664a627bcb7ac430&client_secret=7c5236d1b71d4271700621fd987fc469f269d8a5");
		HttpResponse response = client.execute(request);

		if (response.getStatusLine().getStatusCode() != 200)
			throw new RuntimeException("Feedly API error with return code: " + response.getStatusLine().getStatusCode());
				
		JsonParser parser = new JsonParser();
		InputStreamReader sr = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
		BufferedReader br = new BufferedReader(sr);
		
		
//		Gson gson=new Gson();
//		Object json = (Object) parser.parse(br);
		String json=br.lines().collect(Collectors.joining());
		br.close();
		sr.close();
		
		return json;
	}
	
	private int processSearchFeeds(String uri) throws Exception {
		
		String query = endpoint.getConfiguration().getQuery();
		//String uri = String.format("search/feeds?query=%s", query);
		String json = performGetRequest(uri);
		
//		JsonArray feeds = (JsonArray) json.get("results");
//		List<Feed> feedList = new ArrayList<Feed>();
//		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
//		for (JsonElement f : feeds) {
//			//logger.debug(gson.toJson(i));
//			Feed feed = gson.fromJson(f, Feed.class);
//			feedList.add(feed);		
//		}		
		
        Exchange exchange = getEndpoint().createExchange();
        exchange.getIn().setBody(json, String.class);
        getProcessor().process(exchange); 
        
        return 1;
	}

}
