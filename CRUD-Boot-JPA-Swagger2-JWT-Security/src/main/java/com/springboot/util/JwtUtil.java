package com.springboot.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @apiNote JWT Utility Class
 *
 */
@Component
public class JwtUtil {

	@Value("${jwt.secret.key}")
	private String secretKey;

	@Value("${jwt.validaty}")
	private int validaty;

	/* Token Generator */
	public String tokenGenerator(String username) {
		return Jwts.builder().setSubject(EncryptionDecryption.encrypt(username)).setIssuer("Himanshu")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(validaty)))
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secretKey.getBytes())).compact();
	}

	/* Token reader */
	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(Base64.getEncoder().encode(secretKey.getBytes())).parseClaimsJwt(token)
				.getBody();
	}

	/* Token Expiration Check */
	public boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}
	
	/* Get User name */
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	/* Get Token Expiration Date */
	public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }
	
	public Boolean isTokenValid(String token,String username) {
		return getUsername(token).equals(username) && !isTokenExpired(token);
	}
}
