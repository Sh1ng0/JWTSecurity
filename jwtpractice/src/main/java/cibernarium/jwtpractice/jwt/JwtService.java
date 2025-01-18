package cibernarium.jwtpractice.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY = "4Bd23Gk1M8wD2R0zI7eV9C1qF8yT3JvD5U2Kz0LhZ7PpK1Q";
        // private static final String SECRET_KEY_STRING = new String(SECRET_KEY.getEncoded());


    public String getToken(UserDetails user) {

        return getToken(new HashMap<>(), user);

    }


    public String getToken(Map<String, Object> extraClaims, UserDetails user) {

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();



    }

    private Key getKey(){

        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }


}



