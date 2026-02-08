<<<<<<< HEAD
package springCore.di;

import org.springframework.stereotype.Component;

@Component("smsservice")
public class Smsservice implements Messageservice{
    @Override
    public  void sendMessage(String message){
        System.out.println(message);
    }

}
=======
package springCore.di;

import org.springframework.stereotype.Component;

@Component("smsservice")
public class Smsservice implements Messageservice{
    @Override
    public  void sendMessage(String message){
        System.out.println(message);
    }

}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
