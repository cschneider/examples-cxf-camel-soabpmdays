SOA BPM Days - Examples CXF and Camel
===============================


Prerequisites:
-------------

- Install maven - http://maven.apache.org/
- Install Eclipse - http://www.eclipse.org/downloads/
	Eclipse Classic is enough to start
- Install m2eclipse
	http://m2eclipse.sonatype.org/sites/m2e/

	Note: In eclipse Indigo there is a new version of m2eclipse 1.0 installed. This version is not compatible with code generation plugins.
	So please use m2eclipse 0.12 form the archived m2eclipse releases above.

How to import a maven project:
------------------------------

Start you eclipse and File -> Import -> Maven -> Existing maven projects -> Browse to the project directory  -> Finish
The project will then be imported and the classpath will be setup to include the maven dependencies.

For projects that generate sources like most CXF projects you also have to update the project configuration:
Right click on project -> maven -> update project configuration

The sources will be generated and the new source folder will be added to the eclipse project

CXF Examples:
--------------

The CXF Examples where directly taken from the cxf distribution.
So download the current CXF version:
http://cxf.apache.org/download.html

java_first_jaxws
---------------

Shows how to create a service interface using jax ws annotations, how to create the service implementation and how to start the service and client.
For starting service and client the jaxws standard commands are used. While this follows the standard you should think about using spring or blueprint
instead to better separate the service framework from your business code.

wsdl_first
---------

This example show how to create java code from a wsdl and how to use this generated code to offer and access a service.
The examples has starters for spring and non spring usage.

The client business code is in a spearate class CustomerServiceSpringTester to show how to separate business logic from the technology. 
This class simply gets the servide proxy injected as an interface.

The example also shows several best practices:
- document style
- complex data types like Customer
- lists of complex data types
- how to use exceptions as web faults

jms_spring_config
-----------------

Uses the same wsdl, service and client classes but changes the protocol to jms. A fixed queue is used for the request and a temp queue for the replies.
The example requires a broker to be started before client and server can be started.


Camel Examples:
----------------

example-one
------------

Shows how to create your first own project by using a standard camel archetype.

camel-example2
---------------

Changes the starter to a more understandable code.
Change the project to use OSGi.
This example can be started from eclipse and be deployed to Apache Karaf.

camel-example-debugging
-------------------------

Shows how to use tracing, jmx trace notifiactions and the camel debugger

Testing camel routes by example of the integration tests of the camel-soap component
-----------------------------------------------------------------------------------

Install the camel sources
http://camel.apache.org/source.html

Import camel-soap from components/camel-soap

Take a look at the test cases to learn how to use the camel Testing features for writing integration tests for camel routes.
During the training the "org.apache.camel.dataformat.soap.SoapMarshalTest" was used.

The test uses the org.apache.camel.test.junit4.CamelTestSupport to provide lots of convenience when doing camel testing.
For example a camel context is automatically started and configured with routes. The MockEndpoint is configured using the @EndpointInject annotation.

The MockEndpoint ("mock:") is used to make assertions about the messages that pass the route during the tests.
The test is feeded using a DirectEndpoint ("direct:") which can be triggered using a producerTemplate. The ProcuderTemplate is configured
using the @Produce annotaiton.

camel-example-jaxb
-------------------

This example shows how to serialize custom objects using JAXB and how to send and and receive them. An object is created and then sent
to a jms and a file endpoint. On the receiving side the file or jms queue is read and the result is sent to a Receiver bean. As the bean contains only
one method and the only parameter is JAXB annotated the object is automatically deserialized.
