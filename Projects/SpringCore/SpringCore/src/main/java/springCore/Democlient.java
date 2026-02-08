<<<<<<< HEAD
package springCore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springCore.controller.Democontroller;
import springCore.repository.Demorepository;
import springCore.service.Demoservice;

public class Democlient {
    public static void main(String[] args) {
        ApplicationContext applicationContext =new AnnotationConfigApplicationContext(Appconfig.class);
        Democontroller democontroller=applicationContext.getBean(Democontroller.class);
        System.out.println(democontroller.hello());

        Demoservice demoservice=applicationContext.getBean(Demoservice.class);
        System.out.println(demoservice.hello());

        Demorepository demorepository=applicationContext.getBean(Demorepository.class);
        System.out.println(demorepository.hello());

    }
}
=======
package springCore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springCore.controller.Democontroller;
import springCore.repository.Demorepository;
import springCore.service.Demoservice;

public class Democlient {
    public static void main(String[] args) {
        ApplicationContext applicationContext =new AnnotationConfigApplicationContext(Appconfig.class);
        Democontroller democontroller=applicationContext.getBean(Democontroller.class);
        System.out.println(democontroller.hello());

        Demoservice demoservice=applicationContext.getBean(Demoservice.class);
        System.out.println(demoservice.hello());

        Demorepository demorepository=applicationContext.getBean(Demorepository.class);
        System.out.println(demorepository.hello());

    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
