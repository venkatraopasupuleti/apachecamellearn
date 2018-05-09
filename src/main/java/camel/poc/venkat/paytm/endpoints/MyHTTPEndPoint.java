package camel.poc.venkat.paytm.endpoints;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultPollingEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;

import camel.poc.venkat.paytm.HTTPComponent;
import camel.poc.venkat.paytm.configuration.MyHTTPConfiguration;
import camel.poc.venkat.paytm.consumers.HTTPConsumer;

@UriEndpoint(scheme="myhttp", title="myhttp", syntax="myhttp://operationPath", consumerOnly=true, consumerClass=HTTPConsumer.class, label="feeds")
public class MyHTTPEndPoint extends DefaultPollingEndpoint{
	
	public MyHTTPEndPoint(String uri, String operationPath, HTTPComponent component) {
		super(uri, component);
		this.operationPath = operationPath;
	}
	
	private String operationPath;

	@UriParam
	private MyHTTPConfiguration configuration;

	public Producer createProducer() throws Exception {
		throw new UnsupportedOperationException("FeedlyProducer is not implemented");
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		HTTPConsumer consumer = new HTTPConsumer(this, processor);
        return consumer;
	}

	public boolean isSingleton() {
		return true;
	}

	public String getOperationPath() {
		return operationPath;
	}

	public void setOperationPath(String operationPath) {
		this.operationPath = operationPath;
	}

	public MyHTTPConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(MyHTTPConfiguration configuration) {
		this.configuration = configuration;
	}


}
