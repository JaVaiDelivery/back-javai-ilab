package br.com.javai.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dto.EntregadorLoginDTO;
import br.com.javai.projeto.security.Token;
import br.com.javai.projeto.services.IEntregadorService;

@RestController
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private IEntregadorService service;
	
	@PostMapping("/login")
	public ResponseEntity<Token> realizarLogin(@RequestBody EntregadorLoginDTO dadosLogin) {
		
		Token token = service.gerarTokenDeLogin(dadosLogin);
		if (token != null) {
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(401).build();
	}
}
