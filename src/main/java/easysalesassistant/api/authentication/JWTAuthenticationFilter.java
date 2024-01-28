package easysalesassistant.api.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import easysalesassistant.api.entity.SystemUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //@Value("${authentication.key}")
    private String key;

    public void setKey(String key){
        this.key = key;
    }

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    public JWTAuthenticationFilter(@Value("${authentication.key}") String key){
        this.key = key;
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
        String testKey = this.key;
        Claims claims = Jwts.claims()
                .add("nombre","max")
                .add("tenant",13)
                .build();

        String token = Jwts.builder()
                .subject(authResult.getName())
                .expiration(new Date(System.currentTimeMillis() * 3600000 * 4L))
                .issuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,key.getBytes())
                .claims(claims)
                .compact();


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
        body.put("mensaje","Error en los datos de autenticacion");
        body.put("error",failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }
}
