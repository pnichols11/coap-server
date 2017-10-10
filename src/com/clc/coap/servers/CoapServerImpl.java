/**
 * 
 */
package com.clc.coap.servers;

import org.apache.log4j.Logger;
import org.eclipse.californium.core.CoapServer;

import com.cls.coap.resources.CoapSampleResource;

/**
 * @author pnichols
 *
 */
public class CoapServerImpl implements Runnable {
  private static final Logger LOG=Logger.getLogger(CoapServerImpl.class.getName());
  private int port;

  public CoapServerImpl(int port) {
	  this.port=port;
  }
@Override
public void run() {
  CoapServer coapServer= new CoapServer(this.port);
    coapServer.add(new CoapSampleResource());
    coapServer.start();
    
	
}
  
}
