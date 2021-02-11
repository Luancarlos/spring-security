package br.com.luancarlos.examplejwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ExamplejwtApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExamplejwtApplication.class, args);


        // Criptograr
//        String key = "asd123asd123asd123";
//        String token = Jwts.builder()
//                .setSubject("luancarlos")
//                .signWith(SignatureAlgorithm.HS256, key)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 10000))
//                .compact();
//
//        System.out.println(token);

//    Descriptografar
//        Claims claims = Jwts.parser()
//                .setSigningKey(key)
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdWFuY2FybG9zIiwiaWF0IjoxNjEyNzM5MjI5LCJleHAiOjE2MTI3MzkyMzl9.0F8zFVxMx15GaeODnrIteg4P8TOcrayI4aQjbFJCLjo")
//                .getBody();
//
//        System.out.println("ID: " + claims.getId());
//        System.out.println("Subject: " + claims.getSubject());
//        System.out.println("Issuer: " + claims.getIssuer());
//        System.out.println("Expiration: " + claims.getExpiration());


        // verificar um token

//        try {
//            Jwts.parser().setSigningKey(key).parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdWFuY2FybG9zIiwiaWF0IjoxNjEyNzM5MjI5LCJleHAiOjE2MTI3MzkyMzl9.0F8zFVxMx15GaeODnrIteg4P8TOcrayI4aQjbFJCLjo");
//        } catch (JwtException | IllegalArgumentException e) {
//            System.out.println("Invalid JWT token.");
//            System.out.println("Invalid JWT token trace." + e.getMessage());
//        }

    }

}
