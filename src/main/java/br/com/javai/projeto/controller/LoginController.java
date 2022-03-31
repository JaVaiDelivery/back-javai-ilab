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
import br.com.javai.projeto.util.Message;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private IEntregadorService service;
	
	@ApiOperation(value = "Login do entregador", notes = "Login do entregador")
	@ApiResponses( value = {
			@ApiResponse(code = 400, message = "O campo email é obrigatório"),
			@ApiResponse(code = 400, message = "O campo senha é obrigatório"),
			@ApiResponse(code = 403, message = "Acesso negado")
	})
	@PostMapping("/login")
	public ResponseEntity<?> realizarLogin(@RequestBody EntregadorLoginDTO dadosLogin) {
		
		try {
			
			if(dadosLogin.getEmail() == null) {
				return ResponseEntity.badRequest().body(new Message("O campo email é obrigatório"));
			}
			
			if(dadosLogin.getSenha() == null) {
				return ResponseEntity.badRequest().body(new Message("O campo senha é obrigatório"));
			}
			
			Token token = service.gerarTokenDeLogin(dadosLogin);
			
			if (token != null) {
				return ResponseEntity.ok(token);
			}
			
			return ResponseEntity.status(403).body(new Message("Acesso negado"));
			
		} catch (Exception ex) {
			
			return ResponseEntity.status(400).body(new Message(ex.getMessage()));
		}
	}
}
