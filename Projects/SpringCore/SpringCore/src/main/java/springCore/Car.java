package springCore;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("car")
//@Primary -PROVIDE HIGHER PREFERENCE WHEN THERE ARE BEANS OF SAME TYPE
public class Car implements  Vehicle{
    @Override
    public void move(){
        System.out.println("car is moving");
    }
}
