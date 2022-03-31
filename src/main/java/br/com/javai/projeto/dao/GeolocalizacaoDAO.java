package br.com.javai.projeto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.javai.projeto.dto.GeolocalizacaoDTO;
import br.com.javai.projeto.model.Geolocalizacao;

public interface GeolocalizacaoDAO extends CrudRepository<Geolocalizacao, Integer>{

	@Query("SELECT "
			+ "new br.com.javai.projeto.dto.GeolocalizacaoDTO("
			+ "  geolocalizacao.id,"
			+ "  geolocalizacao.momento,"
			+ "  geolocalizacao.coordenadas,"
			+ "  geolocalizacao.pedido.id,"
			+ "  geolocalizacao.entregador.id) "
			+ "FROM Geolocalizacao as geolocalizacao where geolocalizacao.pedido.id = :idPedido")
	public List<GeolocalizacaoDTO> encontrarPedidosPorGeolocalizacao(@Param("idPedido") Integer idPedido);
	
}