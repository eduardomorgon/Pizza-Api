package br.com.github.eduardomorgon.pizza.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author eduardo
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authenticationManager;
    private final TokenAuthenticationService tokenService;

    public JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager, TokenAuthenticationService tokenService) {
        super(defaultFilterProcessesUrl);
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        AccountCredentials credentials = new ObjectMapper().readValue(request.getInputStream(), AccountCredentials.class);

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String jwt = tokenService.createJWTAuthentication(authResult.getName());
        response.addHeader(tokenService.HEADER_STRING, tokenService.TOKEN_PREFIX + " " + jwt);
    }

}
