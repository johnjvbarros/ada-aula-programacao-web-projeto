package io.spring.start.projetoweb.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.spring.start.projetoweb.model.dto.UsuarioDTO;

@Service
public class JwtService {

	private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String gerarToken(UsuarioDTO usuario) {
		
		return Jwts
		.builder()
		.setSubject(usuario.getUsername())
		.setIssuer(usuario.getNome())
		.setIssuedAt(new Date())
		.signWith(secretKey)
		.compact();
	}
}
