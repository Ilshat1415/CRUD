package ru.liga.crud.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.liga.crud.support.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {
    private static final String HEAD_NAME = "Authorization";
    private static final String AUTH_TYPE = "Bearer";
    @Value("${jwtSecret}")
    private String secret;
    @Value("${jwtValidityInMs}")
    private int validityInMs;
    @Qualifier("customUserDetailsService")
    private final UserDetailsService userDetailsService;

    public JwtUtils(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String parseJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEAD_NAME);
        String tokenStart = AUTH_TYPE + " ";

        if (bearerToken != null && bearerToken.startsWith(tokenStart)) {
            return bearerToken.substring(tokenStart.length());
        }

        return null;
    }

    public boolean validateJwtToken(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt);

            return true;
        } catch (MalformedJwtException | IllegalArgumentException e) {
            log.error("JWT token is expired or invalid. Message: {}", e.getMessage(), e);

            return false;
        }
    }

    public Authentication getAuthentication(String jwtToken) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserNameFromJwtToken(jwtToken));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserNameFromJwtToken(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }

    public String getAuthType() {
        return AUTH_TYPE;
    }
}
