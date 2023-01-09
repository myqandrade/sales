package com.mike;

import com.mike.entities.Customer;
import com.mike.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SalesApplication {
    @Bean
    public CommandLineRunner init(CustomerRepository customerRepository){
        return args -> {
            Customer customer = new Customer();
            customer.setName("Mike");
            customerRepository.save(customer);

            Customer customer2 = new Customer();
            customer2.setName("Mariana");
            customerRepository.save(customer2);

            List<Customer> findALl = customerRepository.findAll();
            findALl.forEach(System.out::println);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}