package agusramdan.demo.otika.security;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {
//    private PasswordEncoder passwordEncoder;
    private UserDomainRepository repository;

    @Override
    public Mono<UserDetails> findByUsername(String username){
        val opt = repository.findById(username);
        if(opt.isPresent()){
            return Mono.just(opt.map((ud-> User.withUsername(username)
                    .password(ud.getPassword())
                    .authorities(ud.getAuthorities().toArray(new String[0]))
                    .build())).get());
        }
        return Mono.empty();
    }
}
