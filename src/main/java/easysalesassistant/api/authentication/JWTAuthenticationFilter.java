package easysalesassistant.api.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.authentication.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private String key;

    public void setKey(String key){
        this.key = key;
    }

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String userName = obtainUsername(request);
        String password = obtainPassword(request);

        if(userName != null && password != null){
            logger.info("Username obtenido");
            logger.info("Password obtenido");
        }else{
            SystemUser userJson = null;
            try {
                userJson = new ObjectMapper().readValue(request.getInputStream(),SystemUser.class);
                userName = userJson.getUserName();
                password = userJson.getPassword();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        userName = userName.trim();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userName,password);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

        String token = AuthenticationService.addToken(authResult.getName(),"123");
        response.addHeader("Authorization","Bearer ".concat(token));
        Map<String, Object> body = new HashMap<String,Object>();
        body.put("token",token);
        body.put("user",authResult.getName());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String,Object> body = new HashMap<String,Object>();
        body.put("message","Error en los datos de autenticacion");
        body.put("error",failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }
}
