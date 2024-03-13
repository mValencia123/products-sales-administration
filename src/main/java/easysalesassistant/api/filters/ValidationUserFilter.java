package easysalesassistant.api.filters;

import easysalesassistant.api.authentication.service.AuthenticationService;
import easysalesassistant.api.context.TenantContext;
import easysalesassistant.api.context.UserContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(2)
public class ValidationUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String userName = AuthenticationService.getUserName((HttpServletRequest) req);
        UserContext.setCurrentUser(userName);
        try {
            chain.doFilter(request, response);
        } finally {
            UserContext.setCurrentUser("");
        }
    }
}