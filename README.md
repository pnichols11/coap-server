# coap-server
<p>
This is a simple CoAP Server example using a thread pool and MQTT as the output destination.
The purpose of this sample is to demonstrate the following:
   1. How to create a simple CoAP Server.
   2. How to create an ExecutorThreadPool to create multiple instances of a CoAP Server listening on various ports.
   3. How to publish data to an MQTT repository.
</p>
<p>
The idea behind this simple project example is to illustrate how to create a fast and reliable ingestion engine. 
The ingestion engine is publishing to an MQTT topic which allows clients to connect to the MQTT topic and receive
information immediately upon the success of the CoAP transaction. Using MQTT would allow processing engines, or IoT devices
to gain access to the inbound information as the CoAP Server is called and invoked.

This is meant as an example only and complex configuration and real word use is not in scope for this sample.
It is a sample only to demonstrate how to accomplish the simple goals for which it was envisioned.
</p>
<p>
<b><u>DEPENDENCIES</b></u>
<p>
<b>
Application Dependencies
</p>
</b>
<p>
   The following is a list of the application software needed to run this example.
</p>
<p>
  <b>1. Apache ActiveMQ 5.x</b> 
</p>
<p>
The application is using Apache ActiveMQ for the MQTT broker. You can download ActiveMQ from <a href="http://activemq.apache.org"> here.</a>
</p>
<p>
   
   <b>2. Java 8 SE </b>
</p>
<p>
              You can also use Java 9 if you wish.
</p>
<p>
         <b> 3. Eclipse Oxygen IDE </b>
</p>
<p>
              You can substitute Eclipse for your favorite IDE (NetBeans, IntelliJ, etc.)
</p>
<p>          
   <b><u>JAVA LIBRARY DEPENDENCIES</u></b> 
</p> 
<p>
          (See pom.xml file)
</p>
<p>
            <b>1. Eclipse Californium</b> 
</p>
<p>
             <a href="https://www.eclipse.org/californium/"> This dependeny is included in the maven pom.xml </a>
</p>
<p>
            <b>2. Apache Log4J</b>
</p>
<p>
            <a href="https://logging.apache.org/log4j/2.x/"> Log 4 J downloads site</a>
</p>
<p>
           <b>3. Apache ActiveMQ client library</b>
</p>
<p>
            <a href="http://activemq.apache.org"> Apache ActiveMQ Web Site</a>
</p>          
<p>
    <b><u> Disclaimer</u> </b>
</p>
<p>

The application example is deployed as is and does not imply warranty or support, or liabiliity.
You are free to use and modify any way you wish.
    
</p>   
  
    
 
