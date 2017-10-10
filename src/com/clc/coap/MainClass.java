/**
 * 
 */
package com.clc.coap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import com.clc.coap.servers.CoapServerImpl;

/**
 * <p>
 * Cotiviti Coap Example with threading. Main Class
 * <p>
 * <p>
 * <p> <b>Description: </b></p>
 * <p>
 *  Used to start Inbound Listening process.
 * </p>
 * <p>
 * Much of this data is hard coded for demo purposes only. Should not be hard-coded in a real world situation,
 * Should use properties files or some other type of configuration management system.
 * </p>
 * 
 * @author Paul W. Nichols
 *
 */
public class MainClass {
   private static final Logger LOG=Logger.getLogger(MainClass.class.getName());
   private static int[] COAP_PORTS = {5683, 5684,5685};
   private static final int THREAD_COUNT=3;
   public static void main (String args[]) {
	   LOG.info("Starting CoapServer.");
	   List<CoapServerImpl> coapServers= new ArrayList<CoapServerImpl>();
	   ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_COUNT);

       for (int i = 0; i < THREAD_COUNT;i++) {
       	  coapServers.add(new CoapServerImpl(COAP_PORTS[i]));
           executor.execute(coapServers.get(i));
       }
       executor.shutdown();   
	  
   }
}
