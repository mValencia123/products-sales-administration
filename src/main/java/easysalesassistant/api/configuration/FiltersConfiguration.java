package easysalesassistant.api.configuration;

import easysalesassistant.api.filters.ValidationTenantFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfiguration {
    @Bean
    public FilterRegistrationBean<ValidationTenantFilter> tenantValidationFilter(){
        FilterRegistrationBean<ValidationTenantFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ValidationTenantFilter());
        registrationBean.addUrlPatterns("/api/category");
        registrationBean.addUrlPatterns("/api/category/**");
        return registrationBean;
    }
}
