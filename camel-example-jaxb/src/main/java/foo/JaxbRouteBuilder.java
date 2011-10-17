/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package foo;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Shows how to easily send pojos over different transports
 */
public class JaxbRouteBuilder extends RouteBuilder {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
    	DefaultCamelContext context = new DefaultCamelContext();
    	
    	/**
    	 * Initialize the JmsComponent. 
    	 * We us the jms url vm://test to install an embedded broker on the fly. So 
    	 * we do not have to start an external broker for the example
    	 */
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://test");
        JmsConfiguration jmsconfig = new JmsConfiguration(connectionFactory);
        JmsComponent jms = new JmsComponent(jmsconfig);
        context.addComponent("jms", jms);
    	
    	context.addRoutes(new JaxbRouteBuilder());
    	
    	/**
    	 * Make sure you add the routes and components BEFORE starting the context
    	 */
        context.start();

        Customer customer = new Customer();
        customer.setName("Christian Schneider");
        customer.setAge(38);
        
        /**
         * Send the object using the file and jms component.
         * As serialization is required the object will be automatically serialized using jaxb  
         */
        ProducerTemplate producer = context.createProducerTemplate();
        producer.sendBody("file://test", customer);
        producer.sendBody("jms://test", customer);
        
        context.shutdown();
    }

    /**
     * Lets configure the Camel routing rules using Java code...
     */
    public void configure() {
        /*
         *  Receive the file or jms message, log the xml content and send to the CustomerReceiver.
         */
        from("file://test").to("log:testfile").bean(new CustomerReciever());
        from("jms://test").to("log:testjms").bean(new CustomerReciever());
    }
}
