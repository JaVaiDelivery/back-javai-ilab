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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
public class PedidoController {
	@Autowired
	private IPedidoService service;
	
	@ApiOperation(value = "Lista todos os pedidos com status em 'EM ABERTO'", notes = "Lista todos os pedidos com status em 'EM ABERTO'")
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Lista de pedidos obtida com sucesso"),
			@ApiResponse(code = 404, message = "O campo nome é obrigatório")
	})
	@GetMapping("/pedidos")
	public ResponseEntity<?> recuperarPedidosEmAberto() {
		
		try {
			List<Pedido> pedidos = service.buscarPedidosEmAberto();
			
			return ResponseEntity.ok(pedidos);
		} catch(Exception ex) {
			return ResponseEntity.status(500).body(new Message(ex.getMessage()));
		}
	}
	
	@ApiOperation(value = "Recupera detalhes do peiddo relacionado ao id informado", notes = "Recupera detalhes do peiddo relacionado ao id informado")
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Dtealhes do pedido recuperados com sucesso"),
			@ApiResponse(code = 400, message = "ID de pedido inválido"),
			@ApiResponse(code = 400, message = "Pedidido não encontrado"),
	})
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
	
	@ApiOperation(value = "Altera informações pedido relacionado ao id informado", notes = "Altera informações do pedido relacionado ao id informado")
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Atribuição concluída"),
			@ApiResponse(code = 404, message = "Entregador nåo encontrado"),
			@ApiResponse(code = 404, message = "Pedido não encontrado")
	})	
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