package br.com.javai.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dto.EntregadorLoginDTO;

//@RestController
//@CrossOrigin("*")
public class LoginController {
//
//	@Autowired
//	private IEntregadorService service;
//	
//	// TODO - criar o Service e importar o IEntregadorService
//	
//	@PostMapping
//	public ResponseEntity<Token> realizarLogin(@RequestBody EntregadorLoginDTO dadosLogin) {
//		
//		// TODO - criar o security, criar o método/função gerarTokenDeEntregadorLogado e importar o Token
//		
//		Token token = service.gerarTokenDeEntregadorLogado(dadosLogin);
//		if (token != null) {
//			return ResponseEntity.ok(token);
//		}
//		return ResponseEntity.status(401).build(); // Esse ERRO tem que sumir quando importar o Token
//	}
}
