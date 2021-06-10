package uz.cas.controllersestem.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import uz.cas.controllersestem.entity.Users;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    private String JwtKey = "cas-secret";

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String generateToken(Authentication authentication) {
        Users user=(Users) authentication.getPrincipal();
        Date now=new Date(System.currentTimeMillis());
        Date expireDate=new Date(now.getTime()+3*24*60*60*1000);  // 1 days
        Map<String,Object> claims=new HashMap<>();
        claims.put("role",user.getRoles());
        claims.put("username",user.getUsername());
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, JwtKey)
                .compact();
    }
    public boolean isValidToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(JwtKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserNameFromToken(String token) {
        Claims username = Jwts.parser()
                .setSigningKey(JwtKey)
                .parseClaimsJws(token)
                .getBody();
        setUsername(username.get("username").toString());
        return String.valueOf(username.get("username"));
    }



}
