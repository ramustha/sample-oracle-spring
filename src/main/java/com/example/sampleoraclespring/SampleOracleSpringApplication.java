package com.example.sampleoraclespring;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SampleOracleSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleOracleSpringApplication.class, args);
    }

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }
}

@RestController
class PersonController {
    private final PersonRepository personRepository;

    PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping(value = "/persons")
    Flux<Person> getPerson() {
        return personRepository.findAll();
    }

    @GetMapping(value = "/persons/{id}")
    Mono<Person> getPerson(@PathVariable String id) {
        return personRepository.findById(id);
    }

    @PostMapping(value = "/persons")
    public Mono<Person> insertPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

}

interface PersonRepository extends R2dbcRepository<Person, String> {

}
