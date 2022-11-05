package com.fairytaler.fairytalecat.jwt;

import com.fairytaler.fairytalecat.exception.TokenException;
import com.fairytaler.fairytalecat.member.dto.MemberDTO;
import com.fairytaler.fairytalecat.member.dto.TokenDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
@Component
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;

    private final UserDetailsService userDetailsService;
    private final Key key;

    public TokenProvider(@Value("${jwt.secret}")String secretKey, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDTO generateTokenDTO(MemberDTO member){

        List<String> roles = Collections.singletonList(member.getMemberRole());

        Claims claims = Jwts
                .claims()
                .setSubject(member.getMemberId())
                .setId(member.getMemberCode().toString());

        claims.put(AUTHORITIES_KEY, roles);
        
        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return new TokenDTO(BEARER_TYPE, member.getMemberName(), accessToken, accessTokenExpiresIn.getTime());
     }

     public String getUserId(String accessToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
     }

     public String getUserCode(String accessToken){
         return Jwts
                 .parserBuilder()
                 .setSigningKey(key)
                 .build()
                 .parseClaimsJws(accessToken)
                 .getBody()
                 .getId();
     }

     public Authentication getAuthentication(String accessToken){

        Claims claims = parseClaims(accessToken);

        if(claims.get(AUTHORITIES_KEY) == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
         System.out.println("claims.get(AUTHORITIES_KEY) = " + claims.get(AUTHORITIES_KEY));

         Collection<? extends GrantedAuthority> authorities =
                 Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                         .map(SimpleGrantedAuthority::new)
                         .collect(Collectors.toList());
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(accessToken));

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public boolean validateToken(String token) throws TokenException {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new TokenException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new TokenException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new TokenException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new TokenException("JWT 토큰이 잘못되었습니다.");
        }
    }

    private Claims parseClaims(String accessToken){
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

}
