package br.com.javai.projeto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dao.EntregadorDAO;
import br.com.javai.projeto.dao.GeolocalizacaoDAO;
import br.com.javai.projeto.dao.PedidoDAO;
import br.com.javai.projeto.model.Entregador;
import br.com.javai.projeto.model.Geolocalizacao;
import br.com.javai.projeto.model.Pedido;
import br.com.javai.projeto.util.Message;

@RestController
@CrossOrigin("*")
public class GeolocalizacaoController {
	
	@Autowired
	private GeolocalizacaoDAO dao;
	
	@Autowired
	private PedidoDAO daoPedido;
	
	@Autowired
	private EntregadorDAO daoEntregador;
	
	@PostMapping("/geolocalizacao")
	public ResponseEntity<?> receberGeolocalizacaoEntregador(@RequestBody Geolocalizacao geo){
		
		try {	
			
			if (geo.getPedido() == null || geo.getPedido().getId() == null || geo.getEntregador() == null || geo.getEntregador().getId() == null) {
				return ResponseEntity.status(400).body(new Message("Todos os campos são obrigatórios"));
			}
			
			Optional<Pedido> pedidoEncontrado = daoPedido.findById(geo.getPedido().getId());
			Optional<Entregador> entregadorEncontrado = daoEntregador.findById(geo.getEntregador().getId());
			
			
			if(pedidoEncontrado.isEmpty()) {
				return (ResponseEntity<?>) ResponseEntity.badRequest().body(new Message("Pedido não encontrado"));
			}
			
			if(entregadorEncontrado.isEmpty()) {
				return (ResponseEntity<?>) ResponseEntity.badRequest().body(new Message("Entregador não encontrado"));
			}
			
			Geolocalizacao nova = dao.save(geo);
			
			return ResponseEntity.ok(nova);
			
		} catch (Exception ex) {
			
			return ResponseEntity.status(400).body(new Message(ex.getMessage()));
		}
	}
}
