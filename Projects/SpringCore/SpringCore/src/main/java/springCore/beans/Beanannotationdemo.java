<<<<<<< HEAD
package springCore.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springCore.di.Messagesender;

class Student{
    private Address address;
    public Student(Address address){
        this.address=address;
    }
    public void print(){
        System.out.println("iam student class ");
        address.print();
    }
    public  void init(){
        System.out.println("initilisation logic");
    }

    public void destroy(){
        System.out.println("destruction logic");
    }
}
 class Address{
           public void print(){
               System.out.println("iam address  class");
           }
        }
        @Configuration
        class Appconfig{
    @Bean(name="addressbean")
    public Address address(){
        return  new Address();
    }
    @Bean(name="studentbean",initMethod = "init",destroyMethod ="destroy" )
    public Student student(){
        return new Student(address());
            }
        }
public class Beanannotationdemo {
    public static void main(String[] args) {
     try( var applicationContext=new AnnotationConfigApplicationContext(Appconfig.class))
        {   // Student student=applicationContext.getBean(Student.class);
         Student student= (Student) applicationContext.getBean("studentbean");
         student.print();}



    }
}
=======
package springCore.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springCore.di.Messagesender;

class Student{
    private Address address;
    public Student(Address address){
        this.address=address;
    }
    public void print(){
        System.out.println("iam student class ");
        address.print();
    }
    public  void init(){
        System.out.println("initilisation logic");
    }

    public void destroy(){
        System.out.println("destruction logic");
    }
}
 class Address{
           public void print(){
               System.out.println("iam address  class");
           }
        }
        @Configuration
        class Appconfig{
    @Bean(name="addressbean")
    public Address address(){
        return  new Address();
    }
    @Bean(name="studentbean",initMethod = "init",destroyMethod ="destroy" )
    public Student student(){
        return new Student(address());
            }
        }
public class Beanannotationdemo {
    public static void main(String[] args) {
     try( var applicationContext=new AnnotationConfigApplicationContext(Appconfig.class))
        {   // Student student=applicationContext.getBean(Student.class);
         Student student= (Student) applicationContext.getBean("studentbean");
         student.print();}



    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
