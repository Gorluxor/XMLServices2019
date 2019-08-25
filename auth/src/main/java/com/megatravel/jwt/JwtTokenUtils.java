package com.megatravel.jwt;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.megatravel.exceptions.CustomException;
import com.megatravel.models.admin.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;



import com.megatravel.security.ImplementedUserDetails;

@Component
public class JwtTokenUtils {
	@Value("${security.jwt.token.secret-key:secret-key-asd-secret-key-asd-secret-key-asd-secret-key-asd-secret-key-asd}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length:86400000}")
	private long validityInMilliseconds = 86400000; // 1d

	@Autowired
	private ImplementedUserDetails myUserDetails;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String email, Role roles) {
		System.out.println(" kreiranje tokena ");
		List<SimpleGrantedAuthority> usersType = new ArrayList<>();	

		usersType.add(new SimpleGrantedAuthority(roles.getRoleName()));

		Claims claims = Jwts.claims().setSubject(email);
		claims.put("auth", usersType);

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.HS256, secretKey)//
				.compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails;
		try{
			userDetails = myUserDetails.loadUserByUsername(getUsername(token));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parse(token);
			return true;
		}catch (SignatureException er){
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
