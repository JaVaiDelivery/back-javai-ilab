package br.com.javai.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.javai.projeto.dao.EntregadorDAO;
import br.com.javai.projeto.dto.EntregadorLoginDTO;
import br.com.javai.projeto.model.Entregador;
import br.com.javai.projeto.security.GeradorDeToken;
import br.com.javai.projeto.security.LoginCrypto;
import br.com.javai.projeto.security.Token;

@Component
public class EntregadorServiceImpl implements IEntregadorService {
	
	@Autowired
	private EntregadorDAO dao;
	
	@Override
	public Token gerarTokenDeLogin(EntregadorLoginDTO dados) {
		Entregador entregador = dao.findByEmail(dados.getEmail());
		
		try {
			if (entregador != null) {
				String senha = LoginCrypto.encriptografar(dados.getSenha());
				
				if (senha.equals(entregador.getSenha())) {
					return new Token(GeradorDeToken.criarToken(entregador));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}