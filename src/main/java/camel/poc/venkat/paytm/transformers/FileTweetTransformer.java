package camel.poc.venkat.paytm.transformers;

public class FileTweetTransformer {
	
	public String removeFullStops(String tweettext) {
		return tweettext.toUpperCase();
	}

}
