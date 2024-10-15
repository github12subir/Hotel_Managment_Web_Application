package com.AirBnb.service.Impl;

import com.AirBnb.entity.AppUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithms.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    private Algorithm algorithm;
    private static final String USER_NAME="username";
    @PostConstruct
    public void postConstrucct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(algorithmKey);
//        System.out.println(issuer);
//        System.out.println(algorithmKey);
//        System.out.println(expiryTime);
    }
    public String generateToken(AppUser user){
       return JWT.create()
               .withClaim(USER_NAME,user.getUsername())
               .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
               .withIssuer(issuer)
               .sign(algorithm);


    }

    public String getUserName(String token){
        //jockey rockey with bodyBuilder vikram
        //verify the token & Decoded the token & stored it into a reference-var of "DecodeddJWT" class
        DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        //get the userName from decoded token & converted it into String & return it
        return decodedJWT.getClaim(USER_NAME).asString();
    }
}
