package net.xdclass.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.xdclass.model.LoginUser;

import java.util.Date;

public class JWTUtil {

    private static final long EXPIRE = 1000L * 60 * 60 * 24 * 7 * 10;

    private static final String SECRET = "131hh";

    private static final String TOKEN_PREFIX = "1024shop";

    private static final String SUBJECT = "zhh";

    /**
     * 根据用户信息生成令牌
     * @param loginUser
     * @return
     */
    public static String geneJsonWebToken(LoginUser loginUser) {

        if (loginUser == null) {
            throw new NullPointerException("对象为空");
        }
        Long id = loginUser.getId();
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img", loginUser.getHeadImg())
                .claim("id", id)
                .claim("name", loginUser.getName())
                .claim("mail", loginUser.getMail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();

        token = TOKEN_PREFIX + token;

        return token;
    }

    /**
     * 校验token的方法
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {

        try {

            final Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

            return claims;

        } catch (Exception e) {
            return null;
        }

    }



}
