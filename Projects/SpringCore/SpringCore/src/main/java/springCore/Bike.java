package springCore;

import org.springframework.stereotype.Component;

@Component("bike")
public class Bike implements Vehicle {
    @Override
    public void move(){
        System.out.println("bike is moving");
    }

}
