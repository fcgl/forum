package com.fcgl.madrid.forum.security;

import com.fcgl.madrid.forum.config.AppProperties;
import com.fcgl.madrid.forum.security.model.JWTBody;
import com.fcgl.madrid.forum.security.model.Role;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private AppProperties appProperties;

    private final static String DEFAULT_ROLE = "ROLE_USER";

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public JWTBody getBodyFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();
        Long userId =  Long.parseLong(claims.getSubject());
        List<String> authorities = new ArrayList<String>();
        List<String> tokenAuthorities = (List<String>) claims.get("authorities");
        if (tokenAuthorities == null) {
            authorities.add(DEFAULT_ROLE);
        } else {
            authorities = tokenAuthorities;
        }
        return new JWTBody(userId, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
