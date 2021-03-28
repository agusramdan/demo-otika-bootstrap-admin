package agusramdan.demo.otika.controller;


import agusramdan.demo.otika.model.domain.Users;
import agusramdan.demo.otika.model.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UsersRestController {

    private UsersRepository repository;

    @GetMapping()
    public Flux<Users> getAll(){
        return Flux.fromIterable(repository.findAll());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Users> create(@RequestBody Users users){
        return Mono.just(repository.save(users));
    }

    @GetMapping("/{username}")
    public Mono<Users> get(@PathVariable String username){
        return Mono.justOrEmpty(repository.findById(username));
    }

    @PutMapping("/")
    public Mono<Users> update(@RequestBody Users users){
        return Mono.just(repository.save(users));
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> delete(@PathVariable String username){
        repository.deleteById(username);
        return Mono.empty();
    }

}
