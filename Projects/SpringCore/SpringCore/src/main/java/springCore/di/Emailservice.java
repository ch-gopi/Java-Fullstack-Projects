package springCore.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("emailservice")
@Primary

public class Emailservice implements Messageservice{
    @Override
    public  void sendMessage(String message){
        System.out.println(message);
    }

}
