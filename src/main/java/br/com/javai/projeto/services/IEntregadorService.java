package br.com.javai.projeto.services;

import br.com.javai.projeto.dto.EntregadorLoginDTO;
import br.com.javai.projeto.security.Token;

public interface IEntregadorService {
	public Token gerarTokenDeLogin(EntregadorLoginDTO dados);
}