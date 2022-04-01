package br.com.javai.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dao.EntregadorDAO;
import br.com.javai.projeto.model.Entregador;
import br.com.javai.projeto.security.LoginCrypto;
import br.com.javai.projeto.util.Message;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
public class EntregadorController {

	@Autowired
	private EntregadorDAO dao;

	@ApiOperation(value = "Cadastra um novo entregador", notes = "Cadastra um novo entregador")
	@ApiResponses( value = {
			@ApiResponse(code = 201, message = "Entregador cadastrado com sucesso"),
			@ApiResponse(code = 400, message = "O campo nome é obrigatório"),
			@ApiResponse(code = 400, message = "O campo email é obrigatório"),
			@ApiResponse(code = 400, message = "O campo senha é obrigatório"),
	})
	@PostMapping("/entregador")
	public ResponseEntity<?> cadastrarEntregador(@RequestBody Entregador entregador) {
		try {
			
			if(entregador.getNome() == null) {
				return ResponseEntity.badRequest().body(new Message("O campo nome é obrigatório"));
			}
			
			if(entregador.getEmail() == null) {
				return ResponseEntity.badRequest().body(new Message("O campo email é obrigatório"));
			}
			
			if(entregador.getSenha() == null) {
				return ResponseEntity.badRequest().body(new Message("O campo senha é obrigatório"));
			}
			
			String senha = LoginCrypto.encriptografar(entregador.getSenha());
			
			entregador.setSenha(senha);
			dao.save(entregador);
			
			return ResponseEntity.ok(entregador);
			
		} catch (Exception ex) {
			return ResponseEntity.status(400).body(new Message(ex.getMessage()));
		}
	}
}
