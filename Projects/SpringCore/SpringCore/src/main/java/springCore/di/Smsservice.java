package springCore.di;

import org.springframework.stereotype.Component;

@Component("smsservice")
public class Smsservice implements Messageservice{
    @Override
    public  void sendMessage(String message){
        System.out.println(message);
    }

}
