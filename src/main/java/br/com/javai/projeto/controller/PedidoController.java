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

import br.com.javai.projeto.model.Pedido;
import br.com.javai.projeto.services.IPedidoService;
import br.com.javai.projeto.util.Message;

@RestController
@CrossOrigin("*")
public class PedidoController {
	@Autowired
	private IPedidoService service;
	
	@GetMapping("/pedidos")
	public ResponseEntity<?> recuperarPedidosEmAberto() {
		
		try {
			List<Pedido> pedidos = service.buscarPedidosEmAberto();
			
			return ResponseEntity.ok(pedidos);
		} catch(Exception ex) {
			return ResponseEntity.status(500).body(new Message(ex.getMessage()));
		}
	}
	
	@GetMapping("/pedidos/{id}")
	public ResponseEntity<?> recuperarPedidoEspecifico(@PathVariable int id) {
		if (id <= 0) {
			return ResponseEntity.badRequest().body(new Message("ID de pedido inválido"));
		}
		
		try {
			Optional<Pedido> pedido = service.buscarPedidoPorId(id);
			
			if (pedido == null) {
				return ResponseEntity.status(404).body(new Message("Pedido não encontrado"));
			}
			
			return ResponseEntity.ok(pedido);
		} catch(Exception ex) {
			return ResponseEntity.status(500).body(new Message(ex.getMessage()));
		}
	}
	
	@PatchMapping("/pedidos/{id}")
	public ResponseEntity<?> atribuirEntregadorEAlterarStatus(@RequestBody Pedido pedido, @PathVariable int id) {
		if (id <= 0) {
			return ResponseEntity.badRequest().body(new Message("ID de pedido inválido"));
		}
		
		try {
			
			boolean res = service.alterarPedido(pedido, id);
			
			if (!res) {
				return ResponseEntity.status(404).body(new Message("Pedido e/ou entregador não encontrado(s)"));
			}
			
			return ResponseEntity.ok("Atribuição concluida");
			
		} catch(Exception ex) {
			return ResponseEntity.status(500).body(new Message(ex.getMessage()));
		}
	}
	
	
}