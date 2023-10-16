package com.example.demo.security.jwt;
import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import com.example.demo.security.service.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${bezkoder.app.jwtSecret}")
  private String jwtSecret;

  @Value("${bezkoder.app.jwtExpirationMs}")
  private int jwtExpirationMs;
  
  
  public String generateJwtToken(Authentication authentication) {
	    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
	    Date now = new Date();
	    Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

	    String token = Jwts.builder()
	            .setSubject(userPrincipal.getUsername())
	            .setIssuedAt(now)
	            .setExpiration(expiryDate)
	            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
	            .compact();

	    return token;
	}

  private Key getSigningKey() {
	    return Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}




  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
               .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
	    try {
	        Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
	        return true;
	    } catch (Exception e) {
	        logger.error("Invalid or expired JWT token: {}", e.getMessage());
	    }

	    return false;
	}

}