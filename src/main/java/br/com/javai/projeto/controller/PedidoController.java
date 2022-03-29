package br.com.javai.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dao.EntregadorDAO;
import br.com.javai.projeto.dao.PedidoDAO;
import br.com.javai.projeto.model.Entregador;
import br.com.javai.projeto.model.Pedido;
import br.com.javai.projeto.util.Message;

@RestController
@CrossOrigin("*")
public class PedidoController {

	@Autowired
	private PedidoDAO dao;
	
	@Autowired
	private EntregadorDAO daoEntregador;
	
	@GetMapping("/pedidos")
	public ResponseEntity<?> recuperarPedidosEmAberto() {
		
		try {
			List<Pedido> pedidos = dao.recuperarPedidosEmAberto();
			return ResponseEntity.ok(pedidos);
		} catch(Exception ex) {
			return ResponseEntity.status(500).body(new Message(ex.getMessage()));
		}
	}
	
	@GetMapping("/pedidos/{id}")
	public ResponseEntity<?> recuperarPedidoEspecifico(@PathVariable int id) {
		
		try {
			Optional<Pedido> pedido = dao.findById(id);
			
			if (pedido.isEmpty()) {				
				return ResponseEntity.status(404).body(new Message("Pedido não encontrado!"));
			}			
			
			return ResponseEntity.ok(pedido);
		} catch(Exception ex) {
			return ResponseEntity.status(500).body(new Message(ex.getMessage()));
		}
	}
	
	
	@PatchMapping("/pedidos/{id}")
	public ResponseEntity<?> atribuirEntregadorEAlterarStatus(@RequestBody Entregador idEntregador, @PathVariable int id) {
		
		Optional<Entregador> encontrado = daoEntregador.findById(idEntregador.getId());
		
		try {
			
			if (encontrado.isEmpty()) {
				return ResponseEntity.status(404).body(new Message("Entregador não encontrado!"));
			}
			
			dao.atribuirEntregadorEMudarStatus(idEntregador.getId(), id);
			
			return ResponseEntity.ok("Atribuição concluida");
			
		} catch(Exception ex) {
			return ResponseEntity.status(500).body(new Message(ex.getMessage()));
		}
	}	
}