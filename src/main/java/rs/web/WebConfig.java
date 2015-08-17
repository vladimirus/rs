package rs.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;

import java.util.Properties;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean(name = "urlViewController")
    public UrlFilenameViewController urlViewController() {
        UrlFilenameViewController urlViewController = new UrlFilenameViewController();
        urlViewController.setPrefix("views/");
        return urlViewController;
    }

    @Bean
    public SimpleUrlHandlerMapping getUrlHandlerMapping() {
        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setOrder(Integer.MAX_VALUE - 2);

        Properties mappings = new Properties();
        mappings.put("/**/*.html", "urlViewController");
        handlerMapping.setMappings(mappings);

        return handlerMapping;
    }
}
