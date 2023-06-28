package com.example.sampleoraclespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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

}

@RestController
class PersonController {
    private final PersonRepository personRepository;

    PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    Flux<Person> getPerson() {
        return personRepository.findAll();
    }

    @PostMapping(value = "/persons",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Person> insertPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

}

interface PersonRepository extends ReactiveCrudRepository<Person, Long> {
}
