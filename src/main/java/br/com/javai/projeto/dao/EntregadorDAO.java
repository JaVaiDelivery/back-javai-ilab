package br.com.javai.projeto.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.javai.projeto.model.Entregador;

public interface EntregadorDAO extends CrudRepository<Entregador, Integer>{
	public Entregador findByEmail(String email);

	public Optional<Entregador> findById(Integer idEntregador);

}