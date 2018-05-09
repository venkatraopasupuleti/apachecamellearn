package camel.poc.venkat.paytm.routebuilders;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.model.rest.RestBindingMode;

import camel.poc.venkat.paytm.aggregation.strategy.HTTPCompletionStrategy;
import camel.poc.venkat.paytm.aggregation.strategy.MyHTTPAggregationStrategy;
import camel.poc.venkat.paytm.processors.FileTweetProcessor;
import camel.poc.venkat.paytm.transformers.HTTPPocRepoTransformer;
import camel.poc.venkat.paytm.transformers.HTTPPocUserTransformer;
import camel.poc.venkat.paytm.transformers.JSONBeautifier;

public class HTTPPocRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("myhttp://users/venkatraopasupuleti?delay=30000")
		.bean(HTTPPocUserTransformer.class,"truncatedata")
		.to("direct:aggregateRoute");
		
		from("myhttp://users/venkatraopasupuleti/repos?delay=30000")
		.bean(HTTPPocRepoTransformer.class,"truncatedata")
		.to("direct:aggregateRoute");
		
		from("direct:aggregateRoute").aggregate(constant(true),new MyHTTPAggregationStrategy())
		.completionPredicate(new HTTPCompletionStrategy())
		.bean(JSONBeautifier.class,"beatifyJSON")
		.process(new FileTweetProcessor())
		.to("file:/tmp?fileName=aggregatedData");

		
	}

}
