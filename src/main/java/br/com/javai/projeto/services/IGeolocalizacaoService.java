package br.com.javai.projeto.services;

import java.util.List;

import br.com.javai.projeto.dto.GeolocalizacaoDTO;
import br.com.javai.projeto.model.Geolocalizacao;

public interface IGeolocalizacaoService {
	public Geolocalizacao inserirGeolocalizacao(Geolocalizacao geo);
	
	public List<GeolocalizacaoDTO> buscarGeolocalizacao(Integer idPedido);
}
