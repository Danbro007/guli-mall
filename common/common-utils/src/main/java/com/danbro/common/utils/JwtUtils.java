package com.danbro.common.utils;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import com.danbro.common.dto.UmsMemberVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

/**
 * @author helen
 * @since 2019/10/16
 */
public class JwtUtils {
    /**
     * 过期时间
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    /**
     * 秘钥
     */
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    public static String getJwtToken(String id, String nickname, String avatar) {

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("guli-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id)
                .claim("nickname", nickname)
                .claim("avatar", avatar)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken jwtToken
     * @return token是否合法
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param request 请求
     * @return token是否合法
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if (StringUtils.isEmpty(jwtToken)) {
                return false;
            }
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     *
     * @param request 请求
     * @return 用户id
     */
    public static UmsMemberVo getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return new UmsMemberVo().setHeader(claims.get("avatar", String.class))
                .setId(claims.get("id", Long.class)).
                setNickname(claims.get("nickname", String.class));
    }
}
