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

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * A Camel Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();
        context.addRoutes(new MyRouteBuilder());
        context.setTracing(true);
        context.start();
        System.in.read();
        context.shutdown();
    }

    /**
     * Lets configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("file:src/data?noop=true").
            choice().
                when(xpath("/person/city = 'London'")).to("file:target/messages/uk").
                otherwise().to("file:target/messages/others");

    }
}
