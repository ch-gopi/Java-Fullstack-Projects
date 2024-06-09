package springCore.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {


        String message = "hii gd mrng have a nice day";
  //      Smsservice smsservice = new Smsservice();
     //   Emailservice emailservice =new Emailservice();
 //       Messagesender messagesender = new Messagesender(smsservice);
 //       messagesender.sendMessage(message);
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(Appconfig.class) ;
        Messagesender messagesender=applicationContext.getBean(Messagesender.class);
        messagesender.sendMessage(message);



    }
}