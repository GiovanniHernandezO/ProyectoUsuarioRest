package com.ejemplo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenUtil {
    public static final String SECRET = "Ei%t@yk4VK5F&r4X%oyqz#MaC^2sfK";
    
    public static String generarToken(UUID id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idUsuario", id);
        return "Bearer " + Jwts.builder()
            .setId(id.toString())
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .setExpiration(new Date(System.currentTimeMillis() + (120 * 60 * 2000)))// 4H
            .compact();
    }
    
    public static String getId(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET)
            .parseClaimsJws(token.split(" ")[1]).getBody();
        return claims.getId();
    }
    
    public static Object getClaims(String token, String value) {
        Claims claims = Jwts.parser().setSigningKey(SECRET)
            .parseClaimsJws(token.split(" ")[1]).getBody();
        return claims.get(value);
    }
}
