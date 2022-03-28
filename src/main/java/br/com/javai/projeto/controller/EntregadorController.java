package br.com.javai.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dao.EntregadorDAO;
import br.com.javai.projeto.model.Entregador;
import br.com.javai.projeto.security.LoginCrypto;

@RestController
@CrossOrigin("*")
public class EntregadorController {

	@Autowired
	private EntregadorDAO dao;

	@PostMapping("/entregador")
	public Entregador cadastrarEntregador(@RequestBody Entregador entregador) {
		try {
			String senha = LoginCrypto.encriptografar(entregador.getSenha());
			entregador.setSenha(senha);
			dao.save(entregador);
			return entregador;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
