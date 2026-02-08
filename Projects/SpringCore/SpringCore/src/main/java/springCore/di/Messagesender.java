<<<<<<< HEAD
package springCore.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Messagesender {

@Autowired
@Qualifier("emailservice")
    private Messageservice messageservice;//FIELD INJECTION
@Autowired
  private Messageservice smsservice;
 //   private Smsservice smsservice;  --Tight coupling
  //  private  Emailservice emailservice;
  //  public Messagesender(Smsservice smsservice){
   //     this.smsservice=smsservice;
  //  }
 //CONSTRUCTOR BASED INJECTION
 /*
    public Messagesender(@Qualifier("emailservice") Messageservice messageservice){
      this.messageservice=messageservice;
      System.out.println("constructor based dependency 1");
    }
    @Autowired
    public Messagesender(@Qualifier("emailservice") Messageservice messageservice,Messageservice smsservice){
        this.messageservice=messageservice;
        this.smsservice=smsservice;
        System.out.println("constructor based dependency 2");
    }*/
 //--Setter based injection
    /*
  @Autowired
    public void setMessageservice(Messageservice messageservice) {
        this.messageservice = messageservice;
      System.out.println("setter based dependency injection 1");
    }
    @Autowired
    public void setSmsservice(Messageservice smsservice) {
        this.smsservice = smsservice;
        System.out.println("setter based dependency injection 2");
    }
*/
    public  void sendMessage(String message){
        this.messageservice.sendMessage(message);
       this.smsservice.sendMessage(message);
    }

}
=======
package springCore.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Messagesender {

@Autowired
@Qualifier("emailservice")
    private Messageservice messageservice;//FIELD INJECTION
@Autowired
  private Messageservice smsservice;
 //   private Smsservice smsservice;  --Tight coupling
  //  private  Emailservice emailservice;
  //  public Messagesender(Smsservice smsservice){
   //     this.smsservice=smsservice;
  //  }
 //CONSTRUCTOR BASED INJECTION
 /*
    public Messagesender(@Qualifier("emailservice") Messageservice messageservice){
      this.messageservice=messageservice;
      System.out.println("constructor based dependency 1");
    }
    @Autowired
    public Messagesender(@Qualifier("emailservice") Messageservice messageservice,Messageservice smsservice){
        this.messageservice=messageservice;
        this.smsservice=smsservice;
        System.out.println("constructor based dependency 2");
    }*/
 //--Setter based injection
    /*
  @Autowired
    public void setMessageservice(Messageservice messageservice) {
        this.messageservice = messageservice;
      System.out.println("setter based dependency injection 1");
    }
    @Autowired
    public void setSmsservice(Messageservice smsservice) {
        this.smsservice = smsservice;
        System.out.println("setter based dependency injection 2");
    }
*/
    public  void sendMessage(String message){
        this.messageservice.sendMessage(message);
       this.smsservice.sendMessage(message);
    }

}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
