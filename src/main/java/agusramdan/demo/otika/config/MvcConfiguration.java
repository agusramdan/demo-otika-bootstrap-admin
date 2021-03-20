package agusramdan.demo.otika.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {



    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/index").setViewName("otika/index");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/forgot").setViewName("login");
        registry.addRedirectViewController("/","/index");
        registry.addRedirectViewController("/index.html","/index");
        registry.addRedirectViewController("/register.html","/register");

        // Otika
        // Forms
        registry.addViewController("/forms-advanced-form.html").setViewName("otika/forms-advanced-form");
        // Tables
        registry.addViewController("/export-table.html").setViewName("otika/export-table");
    }

}
