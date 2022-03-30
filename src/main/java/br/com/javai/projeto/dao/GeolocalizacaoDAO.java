package br.com.javai.projeto.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.javai.projeto.model.Geolocalizacao;

public interface GeolocalizacaoDAO extends CrudRepository<Geolocalizacao, Integer>{
	
}
