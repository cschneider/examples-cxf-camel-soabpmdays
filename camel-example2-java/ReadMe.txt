Camel Example OSGi and standalone
=================================

Replacing the Main.main call by a more understandable initialization of the camel context.

Making our example OSGi ready by adding the maven bundle plugin and changing the packaging to "bundle".

The resulting jar can be simply copied to the Karaf deploy folder.
This also includes the simple.xml blueprint file which can be also copied to the deploy folder.

Make sure you have the camel features added and installed in your Karaf:
> features:addurl mvn:org.apache.camel.karaf/apache-camel/2.8.1/xml/features
> 

See:
    http://camel.apache.org/karaf.html
    
The easiest way to start is using the Talend Integration Factory or the Talend ESB as these already contain and start the camel features.

See:
	http://de.talend.com/products-application-integration/talend-integration-factory-community-edition.php
	