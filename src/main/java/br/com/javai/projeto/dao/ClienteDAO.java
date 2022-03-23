package br.com.javai.projeto.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.javai.projeto.model.Cliente;

public interface ClienteDAO extends CrudRepository<Cliente, Integer>{

}
