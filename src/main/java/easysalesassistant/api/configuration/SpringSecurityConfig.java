package easysalesassistant.api.configuration;

import easysalesassistant.api.authentication.JWTAuthenticationFilter;
import easysalesassistant.api.authentication.JWTAuthorizationFilter;
import easysalesassistant.api.services.ISystemUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SpringSecurityConfig {

    @Value("${authentication.key}")
    private String key;

    @Autowired
    private ISystemUserServiceImp userDetailService;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/login/");
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");
        jwtAuthenticationFilter.setKey(key);

        JWTAuthorizationFilter jwtAuthFilter = new JWTAuthorizationFilter(authenticationManager);
        jwtAuthFilter.setKey(key);
        httpSecurity
                .csrf((c) -> c.disable())
                .sessionManagement((m) -> m.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authz) -> {
                    try {
                        authz.requestMatchers("/api/products/**").permitAll();
                        authz.anyRequest().authenticated();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthFilter,JWTAuthorizationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
}
