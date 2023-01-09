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
            System.out.println("Salvando clientes: ");
            Customer customer = new Customer();
            customer.setName("Mike");
            customerRepository.save(customer);

            Customer customer2 = new Customer();
            customer2.setName("Mariana");
            customerRepository.save(customer2);

            List<Customer> allCustomers = customerRepository.findAll();
            allCustomers.forEach(System.out::println);

            System.out.println("Atualizando clientes: ");

            allCustomers.forEach(c -> {
                c.setName(c.getName() + " atualizado");
                customerRepository.update(c);
            });

            allCustomers.forEach(System.out::println);

            System.out.println("Buscando clientes: ");

            customerRepository.findByName("Mike").forEach(System.out::println);

            System.out.println("Deletando clientes: ");
            customerRepository.findAll().forEach(c -> {
                customerRepository.delete(c);
            });

            allCustomers = customerRepository.findAll();

            if(allCustomers.isEmpty()){
                System.out.println("No costumers found");
            }
            else {
                allCustomers.forEach(System.out::println);
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}