package easysalesassistant.api.authentication.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class AuthenticationService {

    private static final long EXPIRATIONTIME = 864_000_00; // 1 day in milliseconds
    private static final String SECRETKEY = "q3t6w9zCFJNcQfTjWnq3t6w9zCFJNcQfTjWnZr4u7xADGKaPd";
    private static final SecretKey SIGNINGKEY = Keys.hmacShaKeyFor(SECRETKEY.getBytes(StandardCharsets.UTF_8));
    private static final String PREFIX = "Bearer";

    public static String addToken(String username, String tenant) {
        String JwtToken = Jwts.builder()
                .subject(username)
                .audience().add(tenant).and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SIGNINGKEY)
                .compact();
        return PREFIX + " " + JwtToken;
    }

    public static String getTenant(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        String tenant = Jwts.parser()
                .setSigningKey(SIGNINGKEY)
                .build().parseClaimsJws(token.replace(PREFIX, "").trim())
                .getBody()
                .getAudience()
                .iterator()
                .next();
        return tenant;
    }
}