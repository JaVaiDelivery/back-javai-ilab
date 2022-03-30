package br.com.javai.projeto.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import br.com.javai.projeto.model.Entregador;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class GeradorDeToken {
	private static final int SEGUNDOS = 1000;
	private static final int MINUTOS = 60 * SEGUNDOS;
	private static final int HORAS = 60 * MINUTOS;
	private static final int DIAS = 24 * HORAS;
	
	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";
	private static final long EXPIRATION = 30 * MINUTOS;
	private static final String SECRET_KEY = "123456789012345678901234567890 1";
	private static final String EMISSOR = "JaVai";
	
	public static String criarToken(Entregador entregador) {
		Key chaveSecreta = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
		
		String token = Jwts.builder()
				.setSubject(entregador.getEmail())
				.claim("id_entregador", entregador.getId())
				.claim("nome_entregador", entregador.getNome())
				.setIssuer(EMISSOR)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(chaveSecreta, SignatureAlgorithm.HS256)
				.compact();
		
		System.out.println("DEBUG = token gerado: " + token);
		return PREFIX + token;
	}
	
	private static boolean validarExpiracao(Date expiracao) {
		return expiracao.after(new Date(System.currentTimeMillis()));
	}
	
	private static boolean validarEmissor(String emissor) {
		return emissor.equals(EMISSOR);
	}
	
	private static boolean validarSubject(String email) {
		return email != null && email.length() > 0;
	}
	
	public static Authentication validarRequest(HttpServletRequest req) {
		String token = req.getHeader(HEADER);
		token = token.replace(PREFIX, "");
		
		Jws<Claims> jwsClaims = Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY.getBytes())
				.build()
				.parseClaimsJws(token);
		
		String email = jwsClaims.getBody().getSubject();
		String emissor = jwsClaims.getBody().getIssuer();
		Date exp = jwsClaims.getBody().getExpiration();
		
		if (validarSubject(email) && validarEmissor(emissor) && validarExpiracao(exp)) {
			return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
		}
		
		return null;
	}
}