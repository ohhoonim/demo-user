package dev.ohhoonim.component.sign.activity.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.ohhoonim.component.sign.Authority;
import dev.ohhoonim.component.sign.activity.BearerTokenActivity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class BearerTokenService implements BearerTokenActivity {
    private static final Logger log = LoggerFactory.getLogger(BearerTokenService.class);

    private final String zoneOffsetId;
    private final SecretKey key;

    public BearerTokenService(@Value("${jwt.key}") String keyString,
            @Value("${jwt.zone-offset:#{T(java.util.TimeZone).getDefault().toZoneId().getId()}}") String zoneOffsetId) {
        this.key = Keys.hmacShaKeyFor(keyString.getBytes(StandardCharsets.UTF_8));
        this.zoneOffsetId = zoneOffsetId;
    }

    @Override
    public String generateAccessToken(String userName, List<Authority> authorities) {
        return generateToken(userName, authorities, TokenType.ACCESS);
    }

    @Override
    public String generateRefreshToken(String userName, List<Authority> authorities) {
        return generateToken(userName, authorities, TokenType.REFRESH);
    }

    @Override
    public String getUsername(String token) {
        try {
            return getClaims(token).getSubject();
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: {}", e.getMessage());
            throw e;
        } catch (SignatureException | MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Date getExpired(String token) {
        try {
            return getClaims(token).getExpiration();
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: {}", e.getMessage());
            throw e;
        } catch (SignatureException | MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public String generateDenyToken(String userName, List<Authority> authorities) {
        return generateToken(userName, authorities, TokenType.DENY);
    }

    private String generateToken(String username,
            List<Authority> authorities,
            TokenType tokenType) {
        LocalDateTime now = LocalDateTime.now();
        ZoneOffset zoneOffset = ZoneOffset.of(zoneOffsetId);
        Date expiryDate = Date.from(now.plus(tokenType.duration, tokenType.unit).toInstant(zoneOffset));

        return Jwts.builder()
                .subject(username)
                .claim("roles",
                        authorities.stream()
                                .map(Authority::authority)
                                .collect(Collectors.joining(",")))
                .expiration(expiryDate)
                .issuedAt(Date.from(now.toInstant(zoneOffset)))
                .signWith(key)
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public enum TokenType {
        ACCESS(1, ChronoUnit.HOURS),
        REFRESH(7, ChronoUnit.DAYS),
        DENY(-7, ChronoUnit.DAYS); // deny token for a blacklist check

        private final long duration;
        private final ChronoUnit unit;

        TokenType(long duration, ChronoUnit unit) {
            this.duration = duration;
            this.unit = unit;
        }
    }

}