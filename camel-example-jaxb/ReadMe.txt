Camel Example jaxb
==================

Shows how to send annotated Java objects over a queue or any other transport.

How this works:

- The object to be sent has to have jaxb annotations or has to be generated from an xsd
- The camel-jaxb component has to be available on the classpath. It is then automatically detected by camel
- The camel-jaxb component installs a typeconverter that reacts if an annotated object needs to be serialized / deserialized

 
