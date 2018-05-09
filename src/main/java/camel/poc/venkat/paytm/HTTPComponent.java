package camel.poc.venkat.paytm;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;

import camel.poc.venkat.paytm.configuration.MyHTTPConfiguration;
import camel.poc.venkat.paytm.endpoints.MyHTTPEndPoint;

public class HTTPComponent extends UriEndpointComponent{

	public HTTPComponent() {
		super(MyHTTPEndPoint.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		
		MyHTTPEndPoint endpoint = new MyHTTPEndPoint(uri, remaining, this);		
		MyHTTPConfiguration configuration = new MyHTTPConfiguration();
		
		// use the built-in setProperties method to clean the camel parameters map
		setProperties(configuration, parameters);
		
		endpoint.setConfiguration(configuration);		
		return endpoint;
}

}
