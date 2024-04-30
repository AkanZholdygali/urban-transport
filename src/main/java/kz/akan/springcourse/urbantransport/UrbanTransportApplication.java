package kz.akan.springcourse.urbantransport;

import kz.akan.springcourse.urbantransport.repository.StreetRepository;
import kz.akan.springcourse.urbantransport.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrbanTransportApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrbanTransportApplication.class, args);
    }
}
