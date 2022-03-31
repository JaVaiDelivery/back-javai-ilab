package br.com.javai.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.javai.projeto.dao.EntregadorDAO;
import br.com.javai.projeto.dao.GeolocalizacaoDAO;
import br.com.javai.projeto.dao.PedidoDAO;
import br.com.javai.projeto.dto.GeolocalizacaoDTO;
import br.com.javai.projeto.model.Entregador;
import br.com.javai.projeto.model.Geolocalizacao;
import br.com.javai.projeto.model.Pedido;

@Component
public class GeolocalizacaoServiceImpl implements IGeolocalizacaoService {
	@Autowired
	private GeolocalizacaoDAO dao;
	
	@Autowired
	private PedidoDAO daoPedido;
	
	@Autowired
	private EntregadorDAO daoEntregador;

	@Override
	public Geolocalizacao inserirGeolocalizacao(Geolocalizacao geo) {
		Optional<Pedido> pedidoEncontrado = daoPedido.findById(geo.getPedido().getId());
		Optional<Entregador> entregadorEncontrado = daoEntregador.findById(geo.getEntregador().getId());
		
		if (pedidoEncontrado.isEmpty()) {
			return null;
		}
		
		if (entregadorEncontrado.isEmpty()) {
			return null;
		}
		
		Geolocalizacao nova = dao.save(geo);
		
		return nova;
	}

	@Override
	public List<GeolocalizacaoDTO> buscarGeolocalizacao(Integer idPedido) {
		Optional<Pedido> pedidoEncontrado = daoPedido.findById(idPedido);
		
		if (pedidoEncontrado.isEmpty()) {
			return null;
		}
		
		List<GeolocalizacaoDTO> lista = dao.encontrarPedidosPorGeolocalizacao(idPedido);
		
		return lista;
	}

}
