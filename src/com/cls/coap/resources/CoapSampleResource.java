/**
 * 
 */
package com.cls.coap.resources;

import org.apache.log4j.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

import com.clc.coap.mqtt.MQTTPublisher;

/**
 * @author pnichols
 *
 */
public class CoapSampleResource extends CoapResource {
	private static final Logger LOG=Logger.getLogger(CoapSampleResource.class.getName());
	private String payload;
	private static final String RESOURCE_NAME="Coap-Sample-Res";
	private static final String RESPONSE_MSG="{\"RESPONSE\":\"ACK--Received Payload from client.\"}";
	private static final String RESPONSE_ERROR="{\"RESPONSE\":\"ERROR- Error pushing to Topic. Please report to System Administrator.\"}";
	
	  public CoapSampleResource() {
		super(RESOURCE_NAME);
		// TODO Auto-generated constructor stub
	}

	  @Override
      public void handleGET(CoapExchange exchange) {
		  payload=new String(exchange.getRequestPayload());
		  LOG.info("PAYLOAD RECEIVED VIA GET.");
		  LOG.info(payload);
          exchange.respond(RESPONSE_MSG);
      }  
	  
	  @Override
      public void handlePOST(CoapExchange exchange) {
         payload=new String(exchange.getRequestPayload());
         LOG.info("PAYLOAD RECEIVED VIA POST.");
		 LOG.info(payload);
		 boolean result=pushDataToTopic(payload);
		  if(result)
			  exchange.respond(RESPONSE_MSG);
		  else
			  exchange.respond(RESPONSE_ERROR);
      } 
	  
	  @Override
      public void handlePUT(CoapExchange exchange) {
         payload=new String(exchange.getRequestPayload());
         LOG.info("PAYLOAD RECEIVED VIA PUT.");
		 LOG.info(payload);
		 boolean result=pushDataToTopic(payload);
		  if(result)
			  exchange.respond(RESPONSE_MSG);
		  else
			  exchange.respond(RESPONSE_ERROR);
      } 
	  
	  @Override
      public void handleDELETE(CoapExchange exchange) {
        LOG.info("Called delete. Not supported.");
		  exchange.respond("SERVICE INTERFACE NOT SUPPORTED.");
      } 
	  
	  
	  /**
	   * <p><b>PLEASE NOTE:</b></p>
	   * <p>
	   * This unit will not work without having an Active MQTT Broker setup and 
	   * configured. Sorry, I do not have a public one available.
	   * </p>
	   * @param payload
	   * @return
	   */
	  private boolean pushDataToTopic(String payload) {
		boolean result=true;
		  try{
		  Thread t1=new Thread(new MQTTPublisher(payload), "MqttThread");
		  Runtime.getRuntime().addShutdownHook(t1);
		  t1.start();
		 }
		 catch(Exception e){
			 LOG.error("An exception has occurred while attempting to launch MQTT Publisher.");
			 LOG.error("Stack Trace");
			 LOG.error(e);
		 }
		 return result; 
	  } 
}
