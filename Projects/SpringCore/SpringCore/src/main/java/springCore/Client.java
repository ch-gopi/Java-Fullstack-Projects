package springCore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Bike();//loose coupling
        Traveler traveler = new Traveler(vehicle1);
        traveler.startJourney();

        //creating spring IOC CONTAINER
        //READ the configuration class
        //create and manage the spring beans
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
        //retreiving spring beans from spring ioc container
        Car car=applicationContext.getBean(Car.class);
        car.move();

        Bike bike=applicationContext.getBean(Bike.class);
        bike.move();


        Traveler traveler1=applicationContext.getBean(Traveler.class);
        traveler.startJourney();

    }
}