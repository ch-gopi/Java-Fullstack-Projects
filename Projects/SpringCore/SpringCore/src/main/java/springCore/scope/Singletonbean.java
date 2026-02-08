<<<<<<< HEAD
package springCore.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Singletonbean {
    public Singletonbean() {
        System.out.println("singleton bean");
    }
}
=======
package springCore.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Singletonbean {
    public Singletonbean() {
        System.out.println("singleton bean");
    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
