package camel.poc.venkat.paytm.configuration;

import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

@UriParams
public class MyHTTPConfiguration {	
    
    @UriParam
    private String query;
    
    @UriParam(defaultValue = "60000")
    private int delay = 60000;

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}