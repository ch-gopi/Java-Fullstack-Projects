package springCore;

import org.springframework.stereotype.Component;

@Component("cycle")
public class Cycle implements Vehicle{
    @Override
    public void move(){
        System.out.println("cycle is moving");
    }

}
