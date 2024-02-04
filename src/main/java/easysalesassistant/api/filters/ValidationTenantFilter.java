package easysalesassistant.api.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.Map;

//@Component
public class ValidationTenantFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        byte[] inputStreamBytes = StreamUtils.copyToByteArray(request.getInputStream());
        Map<String, String> jsonRequest = new ObjectMapper().readValue(inputStreamBytes, Map.class);
        String requestBodyJsonString = jsonRequest.get("description");
        System.out.println(requestBodyJsonString);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
