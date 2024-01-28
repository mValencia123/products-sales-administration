package easysalesassistant.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration  implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("*/**")
                .allowCredentials(true)
                .allowedMethods("GET","POST","PUT","PATCH","DELETE")
                .allowedOrigins("http://192.168.1.10:9000");
    }
}
