package com.example.finalloanproject;

import com.example.finalloanproject.entities.Loantable;
import com.example.finalloanproject.repositories.LoantableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalLoanProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(FinalLoanProjectApplication.class, args);
	}

	/*@Bean
	CommandLineRunner commandLineRunner(LoantableRepository repo){
        return args -> {
			repo.save(new Loantable(1157l, "Joy", 1300.0, 5, "Business"));
			repo.save(new Loantable(1005l, "May", 1000.0, 6, "Personal"));
			repo.save(new Loantable(1012l, "Alex", 1400.0, 5, "Personal"));
			repo.save(new Loantable(1000l, "Kay", 1500.0, 3, "Business"));
			repo.findAll().forEach(p->{
                System.out.println(p.getClientname());
            });
        };
    }*/

}
