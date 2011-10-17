package foo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Class to be sent. As it has jaxb annotations the camel-jaxb component will automatically serialize / deserialize it
 */
@XmlRootElement
@XmlType
public class Customer {
    String name;
    int age;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
}
