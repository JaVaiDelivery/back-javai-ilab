package br.com.javai.projeto.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.javai.projeto.model.Entregador;

public interface EntregadorDAO extends CrudRepository<Entregador, Integer>{
	public Entregador findByEmail(String email);
}
