<<<<<<< HEAD
package springCore.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Prototypebean {
    public Prototypebean() {
        System.out.println("Prototypebean.Prototypebean");
    }
}
=======
package springCore.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Prototypebean {
    public Prototypebean() {
        System.out.println("Prototypebean.Prototypebean");
    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
