package nmi.api.gateway_server.util;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {


    public static final String SECRET = "19880413A345AB23";


    public void validateToken(final String token) {
        System.out.println(token);
        System.out.println(getSignKey());
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }



    private Key getSignKey() {
        
         byte[] keyBytes = Decoders.BASE64.decode(SECRET);

         if (keyBytes.length < 32) {
            // If the key is too short, pad it with zeros or hash it to make it 32 bytes
            keyBytes = java.util.Arrays.copyOf(keyBytes, 32);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
