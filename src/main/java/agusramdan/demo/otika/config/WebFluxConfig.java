package agusramdan.demo.otika.config;

import lombok.extern.slf4j.Slf4j;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.*;
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@Configuration
@EnableWebFlux
@Slf4j
public class WebFluxConfig implements WebFluxConfigurer {

    @Autowired
    ITemplateResolver thymeleafTemplateResolver;

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        // ...
//
//    }

    @Bean
    public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
        SpringWebFluxTemplateEngine templateEngine = new SpringWebFluxTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver);
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafReactiveViewResolver thymeleafReactiveViewResolver() {
        ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
        viewResolver.setTemplateEngine(thymeleafTemplateEngine());
        return viewResolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafReactiveViewResolver());
    }
    @Bean
    public RouterFunction<ServerResponse> indexRouter() {
        log.info(">>>initializing index.html router bean >>>");
        return RouterFunctions.route(RequestPredicates.GET("/index.html").and(RequestPredicates.accept(MediaType.TEXT_PLAIN))
                , this::index);
    }

    public  Mono<ServerResponse> index(final ServerRequest request) {
        final Map<String,Object> model = Collections.singletonMap("salute", "Hello world from WebFlux.fn");
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("otika/index", model);
    }

    @Bean
    public RouterFunction<ServerResponse> assetsRouter() {
        log.info(">>>initializing images router bean >>>");
        return RouterFunctions.resources("/assets/**", new ClassPathResource("/static/assets/"));
    }

    @Bean
    public RouterFunction<ServerResponse> htmlRouter() {
        log.info(">>>initializing html router bean >>>");
        return RouterFunctions.resources("/*.html", new ClassPathResource("/static/"));
    }

}
