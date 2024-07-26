package com.example.user_management.security;

import com.example.user_management.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret; // application.properties dosyasındaki gizli anahtar değeri

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = Map.of("authorities", userDetails.getAuthorities().stream()
//                .map(authority -> authority.getAuthority()).collect(Collectors.toList()));
//        return createToken(claims, userDetails.getUsername());
//
//    }
public String generateToken(UserDetails userDetails) {
    User user = (User) userDetails; // UserDetails'i User nesnesine çeviriyoruz
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", user.getId());
    claims.put("name", user.getName());
    claims.put("surname", user.getSurname());
    claims.put("identityNumber", user.getIdentityNumber());
    claims.put("birthDate", user.getBirthDate());
    claims.put("salary", user.getSalary());
    claims.put("username", user.getUsername());
    claims.put("email", user.getEmail());
    claims.put("authorities", user.getAuthorities().stream()
            .map(authority -> authority.getAuthority()).collect(Collectors.toList()));
    return createToken(claims, user.getUsername());
}




    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *60 *10))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}




