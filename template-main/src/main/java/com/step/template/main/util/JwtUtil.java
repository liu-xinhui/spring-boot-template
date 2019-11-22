package com.step.template.main.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * https://github.com/auth0/java-jwt
 */
public class JwtUtil {
    private static final String SECRET = "STEP_template";

    /**
     * 新建token
     */
    public static String createToken(int userId) {
        Date expiresDate = DateUtils.addDays(new Date(), 7);//过期时间七天
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer("step")
                .withExpiresAt(expiresDate)
                .withClaim("userId", userId)
                .sign(algorithm);
    }

    /**
     * 获取userId
     */
    public static int getUserId(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("step")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Claim claim = jwt.getClaim("userId");
        return claim.asInt();
    }
}
