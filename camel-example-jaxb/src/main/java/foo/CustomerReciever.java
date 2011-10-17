package foo;

/**
 * CustomerReceiver is a plain pojo. When used with the bean component a lot of convention over
 * configuration magic happens. 
 * 
 * As this class has only one public method camel knows that it has to use the receive method.
 * The method has exactly one in parameter so camel knows the body should be put into the customer parameter.
 * Camel will use the Typeconverter to convert the body into the Customer class. As the source is xml and
 * the customer class is annotated using jaxb it will be automatically deserialized
 */
public class CustomerReciever {

    public void receive(Customer customer) {
        System.out.println("Received a customer named" + customer.getName());
    }
}
