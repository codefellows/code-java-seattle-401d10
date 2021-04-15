package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    static int counter = 0;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    // incoming message, sends out a Greeting java object which is converted by stomp websocket to json
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @MessageMapping("/counter")
    @SendTo("/topic/abnoxiouscounter")
    public CounterPOJO count() {
        counter++;
        return new CounterPOJO(counter);
    }
}
