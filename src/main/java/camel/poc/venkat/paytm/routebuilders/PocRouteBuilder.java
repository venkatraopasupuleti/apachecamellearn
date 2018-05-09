package camel.poc.venkat.paytm.routebuilders;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.TwitterComponent;

import camel.poc.venkat.paytm.processors.FileTweetProcessor;
import camel.poc.venkat.paytm.transformers.FileTweetTransformer;


public class PocRouteBuilder extends RouteBuilder{
	
	private String[] twitterArgs;
	
		
	

	public PocRouteBuilder(String[] args) {
		super();
		this.twitterArgs = args;
	}
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("file:/tmp/tweetstopost?move=./done")
		.process(new FileTweetProcessor())
		.bean(new FileTweetTransformer(), "removeFullStops")
		.to(String.format("twitter:timeline/user?consumerKey=%s&consumerSecret=%s&accessToken=%s&accessTokenSecret=%s",this.twitterArgs ));
		
	}

}
