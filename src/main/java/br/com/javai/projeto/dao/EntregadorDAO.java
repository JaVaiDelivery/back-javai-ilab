package br.com.javai.projeto.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.javai.projeto.model.Entregador;
import br.com.javai.projeto.model.Geolocalizacao;

public interface EntregadorDAO extends CrudRepository<Entregador, Integer>{
	public Entregador findByEmail(String email);

	public List<Geolocalizacao> findAllById(Integer idEntregador);
	
	
//	public List<Geolocalizacao> receberGeolocalizacaoDoEntregador(Integer idEntregador);
}
