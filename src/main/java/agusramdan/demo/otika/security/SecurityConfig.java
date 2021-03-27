package agusramdan.demo.otika.security;


import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;


@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfig {
    private PasswordEncoder passwordEncoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private static final String[] AUTH_WHITELIST = {
            "/resources/**",
            "/webjars/**",
            "/authorize/**",
            "/favicon.ico",
    };

//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user@ramdan.com")
//                .password("user")
//                .roles("USER")
//                .build();
//
//        return new MapReactiveUserDetailsService(user);
//    }
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/assets/**","/h2-console","/h2-console/**"
                        ,"/forgot-password.html","/forgot-password"
                        ,"/register.html","/register",
                        "/actuator", "/actuator/**", "/info/**","/login").permitAll()
                .matchers(
                        PathRequest.toStaticResources().atCommonLocations()

                )
                .permitAll()
                .anyExchange().authenticated()
                .and()
                .csrf().disable()
                //.httpBasic().and()
                .formLogin()
                .loginPage("/login")
                .and().logout()
                .logoutUrl("/logout")
        ;

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }
}
