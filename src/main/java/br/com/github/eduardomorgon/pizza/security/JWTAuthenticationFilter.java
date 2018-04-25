package br.com.github.eduardomorgon.pizza.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author eduardo
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

    private final TokenAuthenticationService tokenService;

    public JWTAuthenticationFilter(TokenAuthenticationService tokenService) {
        this.tokenService = tokenService;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            Authentication authentication = tokenService.getAuthentication(httpRequest.getHeader(tokenService.HEADER_STRING));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (JwtException | IllegalArgumentException e) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, createMessageException(e));
        }
    }

    private String createMessageException(RuntimeException e) {
        if (e instanceof ExpiredJwtException) {
            return "Token Expirado";
        } else if (e instanceof UnsupportedJwtException) {
            return "Token não suportado";
        } else if (e instanceof MalformedJwtException) {
            return "Token não formatado corretamente";
        } else if (e instanceof SignatureException) {
            return "Erro na assinatura do token";
        } else {
            return "Token ilegal";
        }
    }

}
