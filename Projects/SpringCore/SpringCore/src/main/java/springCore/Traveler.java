<<<<<<< HEAD
package springCore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Traveler {
    private Vehicle vehicle;
  //  Car car=null;
   // Bike bike  //Tightly coupled
   // Cycle cycle;
    @Autowired
    public Traveler(@Qualifier("car") Vehicle vehicle){
        this.vehicle=vehicle;
       // this.car=new Car();
       // this.bike =new Bike();
      //  this.cycle =new Cycle();

    }
    public void startJourney(){
         this.vehicle.move();

    }
}
=======
package springCore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Traveler {
    private Vehicle vehicle;
  //  Car car=null;
   // Bike bike  //Tightly coupled
   // Cycle cycle;
    @Autowired
    public Traveler(@Qualifier("car") Vehicle vehicle){
        this.vehicle=vehicle;
       // this.car=new Car();
       // this.bike =new Bike();
      //  this.cycle =new Cycle();

    }
    public void startJourney(){
         this.vehicle.move();

    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
