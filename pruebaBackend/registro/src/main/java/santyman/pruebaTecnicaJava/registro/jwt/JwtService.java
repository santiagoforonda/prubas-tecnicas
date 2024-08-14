package santyman.pruebaTecnicaJava.registro.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import santyman.pruebaTecnicaJava.registro.auth.LoginRequest;


@Service
public class JwtService {

    //Esta clase se encarga de contruir el token
    private static final String SECRET_KEY = "thisisaverysecurekeyformyjwtwithhmacsha256sdsdsdfhgdfd";

    public String getToken(LoginRequest user) {
        return createToken(new HashMap<>(), user);
    }

    private String createToken(Map<String, Object> extraClaims, LoginRequest user) {
            return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt( new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey() , SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
       byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }


    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getKey())
            .build().parseClaimsJws(token)
            .getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

}
