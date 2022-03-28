package br.com.javai.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dao.PedidoDAO;
import br.com.javai.projeto.model.Pedido;

@RestController
public class PedidoController {

	@Autowired
	private PedidoDAO dao;
	
	@GetMapping("/pedidos")
	public ResponseEntity<List<Pedido>> recuperarPedidosEmAberto() {
		try {
			List<Pedido> pedidos = dao.recuperarPedidosEmAberto();
		return ResponseEntity.ok(pedidos);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/pedidos/{id}")
	public ResponseEntity<?> recuperarPedidoEspecifico(@PathVariable int id) {
		try {
			Optional<Pedido> pedido = dao.findById(id);
			if (pedido == null) {
				return ResponseEntity.status(404).body("Pedido não encontrado!");
				// TODO - Criar Util de respostas
			}
			return ResponseEntity.ok(pedido);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}