package org.samtuap.inong.domain.seller.jwt.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class JwtValidator {
    private final SecretKeyFactory secretKeyFactory;

    public String validateToken(String token) {
        try {
            getClaims(token);
            return "VALID_JWT";
        } catch(Exception e) {
            return "INVALID_JWT";
        }
    }

    public long getSellerIdFromToken(String token) {
        Claims claims;
        try {
            claims = getClaims(token);
        } catch(Exception e) {
            throw new RuntimeException("INVALID_TOKEN");
        }
        return Long.parseLong(claims.get("sellerId").toString());
    }

    // 정상적인 토큰인지 판단하는 메서드
    public boolean isValidToken(String token) {
        return StringUtils.hasText(token) && validateToken(token).equals("VALID_JWT");
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKeyFactory.createSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
