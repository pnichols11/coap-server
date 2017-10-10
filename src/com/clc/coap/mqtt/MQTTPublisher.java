/**
 * 
 */
package com.clc.coap.mqtt;
import java.util.Date;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.fusesource.hawtbuf.AsciiBuffer;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Future;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

/**
 * <p>
 * <b>
 * Description
 * </b>
 * </p>
 * <p>
 * This unit requires that you have an Active MQTT Broker running. I am providing a 
 * default localhost address, but you will need to create your own broker and credentials.
 * <p>
 * <p>
 * We are assuming a default Out of the Box ActiveMQ instance for this connection, but you
 * are free to use any MQTT broker supported by FuseSource.
 * </p>
 * @author Paul W Nichols
 *
 */
public class MQTTPublisher implements Runnable{
   private String payload;
   private static final Logger LOG=Logger.getLogger(MQTTPublisher.class.getName());
   private static final String TOPIC_NAME="/cotviti/coap-server";
   /*
    * Note: You MUST HAVE A MQTT BROKER FOR THIS TO WORK. 
    * You could download and install an ActiveMQ instance or use a Docker image..
    * You will get an error until you set up a MQTT broker.
    */
   private static final String MQTT_HOST="tcp://localhost:1883";
   private static final String MQTT_USER="admin";
   private static final String MQTT_PASSWORD="password";
   private static final int PORT=1883;
   
   public MQTTPublisher (String payload) {
	   this.payload=payload;
   }
  	
	@Override
	public void run() {
	  LOG.info("Entering MqttPublisher.run()"); 
	  final String destination = TOPIC_NAME;
	  Buffer msg = new AsciiBuffer(payload);
	        try{
		      MQTT mqtt = new MQTT();
		        mqtt.setHost(MQTT_HOST);
		        mqtt.setUserName(MQTT_USER);
		        mqtt.setPassword(MQTT_PASSWORD);

		        FutureConnection connection = mqtt.futureConnection();
		           connection.connect().await();
		           if(! connection.isConnected()){
		        	   connection.connect();
		           }

		        final LinkedList<Future<Void>> queue = new LinkedList<Future<Void>>();
		        UTF8Buffer topic = new UTF8Buffer(destination);
		        
		            // Send the publish without waiting for it to complete. This allows us
		            // to send multiple message without blocking..
		            queue.add(connection.publish(topic, msg, QoS.AT_MOST_ONCE, false));

		            // Eventually we start waiting for old publish futures to complete
		            // so that we don't create a large in memory buffer of outgoing message.s
		       LOG.info(new Date().toString());
		       LOG.info("Message sent:");
		       LOG.info(payload);
		       // queue.add(connection.publish(topic, new AsciiBuffer("SHUTDOWN"), QoS.AT_LEAST_ONCE, false));
		       LOG.info("Message published to queue was: ");
		       LOG.info(payload);
		        connection.disconnect().await();
		        
		        
		        	
		     }
		     catch(Exception e){
		    	 LOG.info(new Date().toString());
		    	 LOG.error("Exception while attempting to publish to QUeue.");
		    	 LOG.error("Stack Trace");
		    	 LOG.error(e);
		     }
		     
		    }

}
