package Bug.Tracking.System.Application.Bug.Tracking.Application.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
<<<<<<< HEAD
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
=======
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
import java.security.Key;
import java.util.Date;

@Component
<<<<<<< HEAD
public class JwtTokenProvider extends JwtAuthenticationEntryPoint implements IJwtTokenProvider,IJwtAuthenticationFilter {
=======
public class JwtTokenProvider {
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;


    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // Get username from JWT token
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();

        return username;
    }

    // Validate JWT Token
    public boolean validateToken(String token){
        Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parse(token);
        return true;
    }

<<<<<<< HEAD
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

    }
=======
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
}
