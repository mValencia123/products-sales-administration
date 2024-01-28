package easysalesassistant.api.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private String key;

    public void setKey(String key){
        this.key = key;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if(!requireAuthoritation(header)){
            chain.doFilter(request,response);
            return;
        }
        boolean isValidToken = false;
        Claims payload = null;
        String token = header.replace("Bearer ","");
        System.out.println("Token del usuario "+token);
        try {
             payload = Jwts.parser()
                    .setSigningKey(key.getBytes())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            System.out.println("Usuario: " + payload.getSubject());
            isValidToken = true;
        } catch (JwtException e) {
            isValidToken = false;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
        if(isValidToken){
            usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(payload.getSubject(),null, Collections.emptyList());
        }
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request,response);
    }

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public boolean requireAuthoritation(String header){
        if(header == null)
            return false;
        if(!header.startsWith("Bearer "))
            return false;
        return true;
    }
}
