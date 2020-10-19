package com.riviewz.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This class provides method in order to generate and validate JSON Web Tokens
 */
@Component
public class JwtUtils {

    public String createJwt(String email, int userId, String name, Date date) throws UnsupportedEncodingException{
        
    	date.setTime(date.getTime() + (900*1000)); //1000 millisecond = 1 sec -> Here 900, 000 milliseconds = 900 secs = 15 min
        return generateJwt(email, userId, name, date);
    
    }
    
    public String generateJwt(String email, int userId, String name, Date date) throws java.io.UnsupportedEncodingException {

        String jwt = Jwts
        			 .builder()
        			 .setSubject(email)
        			 .setExpiration(date)
        			 .claim("userId", userId)
        			 .claim("name", name)
        			 .signWith(SignatureAlgorithm.HS256, "myPersonalSecretKey12345".getBytes("UTF-8"))
        			 .compact();
        
        System.out.println("JWT------------------------------" + jwt);

        return jwt;
    }


    public Map<String, Object> jwt2Map(String jwt) throws java.io.UnsupportedEncodingException, ExpiredJwtException {

        Jws<Claims> claim = Jwts
        					.parser()
        					.setSigningKey("myPersonalSecretKey12345".getBytes("UTF-8"))
        					.parseClaimsJws(jwt);

        int userId  = claim.getBody().get("userId", Integer.class);
        String name = claim.getBody().get("name", String.class);

        System.out.println("userId = " + userId);
        System.out.println("name = " + name);
        
        Date expDate = claim.getBody().getExpiration();
        String email = claim.getBody().getSubject();

        Map<String, Object> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("userId", userId);
        userData.put("name", name);
        userData.put("exp_date", expDate);

        Date now = new Date();
        if (now.after(expDate)) {
            throw new ExpiredJwtException(null, null, "Session expired!");
        }

        return userData;
    }


    /**
     * this method extracts the jwt from the header or the cookie in the Http request
     *
     * @param request
     * @return jwt
     */
    public String getJwtFromHttpRequest(HttpServletRequest request) {
        
    	String jwt = null;
        
    	if (request.getHeader("jwt") != null) {
            jwt = request.getHeader("jwt");     //token present in header
        } else if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies(); //token present in cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    jwt = cookie.getValue();
                }
            }
        }
        return jwt;
    }

    public Map<String, Object> verifyJwtAndGetData(String jwt) throws  Exception {
        
        System.out.println("JWT*****************************************************************************************************" + jwt);
        if(jwt == null){
            throw  new Exception();
        }
        return jwt2Map(jwt);
    }    
}