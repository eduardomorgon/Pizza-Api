package br.com.github.eduardomorgon.pizza.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author eduardo
 */
@Component
public class TokenAuthenticationService {

    // EXPIRATION_TIME = 10 dias
    static final long EXPIRATION_TIME = 860_000_000;
    static final String SECRET = "MySecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public String createJWTAuthentication(String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }

    public Authentication getAuthentication(String authorization) {
        
        Optional<String> authorizationOptional = Optional.ofNullable(authorization);

        return authorizationOptional
            .map(t -> t.replace(TOKEN_PREFIX, ""))
            .map(t -> Jwts.parser().setSigningKey(SECRET).parseClaimsJws(t).getBody().getSubject())
            .map(username -> new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()))
            .orElse(null);

    }

}
