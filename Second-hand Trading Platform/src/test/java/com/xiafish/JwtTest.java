package com.xiafish;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootTest
class JwtTest {
	@Test
	public void testGenJwt(){
		Map<String,Object> claims=new HashMap<>();
		claims.put("id",2);

		String jwt= Jwts.builder()
				.signWith(SignatureAlgorithm.HS256,"XiaXiaoYu")//签名算法
				.setClaims(claims)//自定义内容（载荷）
				.setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置有效期为1h
				.compact();
		System.out.println(jwt);
	}

	@Test
	public void testParseJwt(){

		String jwt="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MjMsImV4cCI6MTY5MDEyNTI2Mywic3RhdHVzIjoxfQ.X5BmessUp6aBvFTjVSggcDDMQl8uLTnzXnMEk7zfGLc";
		Claims claims=Jwts.parser()
				.setSigningKey("XiaXiaoYu")
				.parseClaimsJws(jwt).getBody();
		System.out.println(claims);
	}

}
