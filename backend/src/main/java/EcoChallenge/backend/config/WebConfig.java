package EcoChallenge.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // URL du front-end
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Autorise PUT et autres méthodes
                        .allowedHeaders("*") // Autorise tous les en-têtes
                        .allowCredentials(true); // Si des cookies ou des sessions sont utilisés
            }
        };
    }
}
