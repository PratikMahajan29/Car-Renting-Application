package ma.inpt.rentingCarApp;

import ma.inpt.rentingCarApp.entities.Car;
import ma.inpt.rentingCarApp.entities.User;
import ma.inpt.rentingCarApp.services.CarService;
import ma.inpt.rentingCarApp.services.NotificationService;
import ma.inpt.rentingCarApp.services.UserService;
import ma.inpt.rentingCarApp.utils.MidnightApplicationRefresh;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class CarRentingApplication {

    // class attributes :
    final CarService carService;
    final UserService userService;
    final NotificationService notificationService;
    final BCryptPasswordEncoder pwEncoder;
    final MidnightApplicationRefresh midAppRef;

    // class constructor :
    public CarRentingApplication(CarService carService, UserService userService, NotificationService notificationService, BCryptPasswordEncoder pwEncoder, MidnightApplicationRefresh midAppRef) {
        this.carService = carService;
        this.userService = userService;
        this.notificationService = notificationService;
        this.pwEncoder = pwEncoder;
        this.midAppRef = midAppRef;
    }

    // class methods :
    public static void main(String[] args) {
        SpringApplication.run(CarRentingApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {

            User user1 = new User("Admin", pwEncoder.encode("admin"), "admin123@gmail.com", "Admin", "Admin", "Admin, Admin, Admin", "8989898989", "admin");
            user1.setRole("ROLE_ADMIN");

            User user2 = new User("pratik", pwEncoder.encode("pratik"), "pratikmahajan1909@gmail.com", "pratik", "mahajan", "nashik", "8989898989", "nashik");
            user2.setRole("ROLE_EMPLOYEE");

            User user3 = new User("ashish", pwEncoder.encode("ashish"), "ashish123@gmail.com", "ashish", "Kushwaha", "mumbai", "8989898989", "Mumbai");
            user3.setRole("ROLE_USER");




         
            userService.save(user1);
            userService.save(user2);
            userService.save(user3);



            Car car1 = new Car("Corolla : Toyota", "Pratik Mahajan", 2001, 1);
            Car car2 = new Car("308 : Peugeot", "King George", 2000, 1);
            Car car3 = new Car("Tucson : Hyundai", "Prathamesh Dhas", 2012, 3);
            Car car4 = new Car("Focus : Ford", "", 2007, 2);
            Car car5 = new Car("Astra : Opel", "Houda Oukessou", 2013, 3);


            carService.save(car1);
            carService.save(car2);
            carService.save(car3);
            carService.save(car4);
            carService.save(car5);


            car5.setTheUser(user3);
            car3.setReturnDate(LocalDate.of(2021, 5, 23));

            car3.setTheUser(user3);
            car4.setReturnDate(LocalDate.of(2021, 12, 28));

            car1.setTheUser(user3);
            car1.setReturnDate(LocalDate.of(2021, 5, 05));

            user3.setCars(Arrays.asList(car1, car1));

            carService.save(car1);
            carService.save(car1);
            userService.save(user3);

            midAppRef.midnightApplicationRefresher();
        };
    }
}