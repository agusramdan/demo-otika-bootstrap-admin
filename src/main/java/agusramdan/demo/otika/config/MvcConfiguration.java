package agusramdan.demo.otika.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addRedirectViewController("/","/index");
        registry.addRedirectViewController("/index.html","/index");
        registry.addRedirectViewController("/register.html","/register");
        registry.addRedirectViewController("/forgot-password.html","/forgot-password");

        // otika
        registry.addViewController("/export-table.html").setViewName("otika/export-table");
    }

}
