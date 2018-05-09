package camel.poc.venkat.paytm.routebuilders;

import org.apache.camel.builder.RouteBuilder;

import camel.poc.venkat.paytm.aggregation.strategy.CompletionStrategy;
import camel.poc.venkat.paytm.aggregation.strategy.MyFileAggregationStrategy;
import camel.poc.venkat.paytm.processors.FileDataProcessor;
import camel.poc.venkat.paytm.transformers.FileDataTransformer;

public class MultiProcessingRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		//reading from party1
		from("file:/tmp/aggregator?fileName=party1.json")
		.bean(FileDataTransformer.class, "convertToJSON")
		.to("direct:aggregateRoute");
		
		//reading from party2
		from("file:/tmp/aggregator?fileName=party2.json")
		.bean(FileDataTransformer.class, "convertToJSON")
		.to("direct:aggregateRoute");
		
		//reading from aggregateRoute
		from("direct:aggregateRoute")
	        .aggregate(constant(true), new MyFileAggregationStrategy())
	        .completionPredicate(new CompletionStrategy())
	        .process(new FileDataProcessor());
	}

}
