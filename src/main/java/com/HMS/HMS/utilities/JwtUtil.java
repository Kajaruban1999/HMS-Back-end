package com.HMS.HMS.utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    private final String jwtSecret = "4e9ad88684aaa187ba9fc4bdf9f9c0ff598966f006d7a80da003b5e093ae446dd4317737a5877b09ae8ac1d3cadd259ab5784d40a8f6d4bd1063e837b787cef4ffefa3fdebbaef026b082bd3b890a6374685223909ebac56a91058322ef731d05e74faa36b73afaf7a8b969a7d1b040d1dca3d6b5923b5a1ca64833d03799b669e1e854eb962f34ea37cdeaf4da368c20987c0e5a008928abb786343c182301af878d3cdf23bb1d620a944534332804fb9092303a3cdb241d858d70019e140925910d207e47da85393eeb7e3dd6bf418c154e921d622f101a61f9dd1740130997ef18b5168dda992273f9854938b4cea66373e4fab6a9e3b176ebb35e08367bd";

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();

        long jwtExpirationDate = 1200000000;
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key key(){
        //  byte[] d = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    public String getEmail(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token){
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;

    }
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


}
