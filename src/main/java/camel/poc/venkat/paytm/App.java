package camel.poc.venkat.paytm;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import camel.poc.venkat.paytm.routebuilders.HTTPPocRouteBuilder;
import camel.poc.venkat.paytm.routebuilders.MultiProcessingRouteBuilder;
import camel.poc.venkat.paytm.routebuilders.PocRouteBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	org.apache.camel.main.Main main = new org.apache.camel.main.Main();
    	main.enableHangupSupport();
    	HTTPPocRouteBuilder httpPocRouteBuilder=new HTTPPocRouteBuilder();
    	CamelContext camelContext=new DefaultCamelContext();
    	camelContext.addComponent("myhttp", new HTTPComponent());
    	try {
    		camelContext.addRoutes(httpPocRouteBuilder);
    		camelContext.start();
			main.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

//backup
//if(args.length<4) {
//	System.err.println("Less number of arguments");
//	System.exit(-1);
//}
////String twitterConsumerKey=args[1];
////String twitterConsumerSecret=args[2];
////String twitterApiKey=args[3];
////String twitterApiSecret=args[4];
//PocRouteBuilder pocRouteBuilder=new PocRouteBuilder(args);
//MultiProcessingRouteBuilder multiprocessingRouteBuilder=new MultiProcessingRouteBuilder();
//HTTPPocRouteBuilder httpPocRouteBuilder=new HTTPPocRouteBuilder();
//CamelContext camelContext=new DefaultCamelContext();
//try {
//	//camelContext.addRoutes(pocRouteBuilder);
//	//camelContext.addRoutes(multiprocessingRouteBuilder);
//	camelContext.addRoutes(httpPocRouteBuilder);
//	do {
//		camelContext.start();
//		Thread.sleep(10000l);
//		camelContext.stop();
//	}while(false);
//} catch (Exception e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}