package br.com.javai.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dto.GeolocalizacaoDTO;
import br.com.javai.projeto.model.Geolocalizacao;
import br.com.javai.projeto.services.IGeolocalizacaoService;
import br.com.javai.projeto.util.Message;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
public class GeolocalizacaoController {
	
	@Autowired
	private IGeolocalizacaoService service;
	
	@ApiOperation(value = "Consulta geolocalização pelo id do pedido", notes = "Consulta geolocalização pelo id do pedido")
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Lista de geolocalização do pedido recupera com sucess0"),
			@ApiResponse(code = 400, message = "ID do pedido inválido"),
			@ApiResponse(code = 400, message = "Pedido não encontrado")
	})
	@GetMapping("/geolocalizacao/{idPedido}")
	public ResponseEntity<?> consultarGeolocalizacaoPorIdPedido(@PathVariable Integer idPedido) {
		if (idPedido <= 0) {
			return ResponseEntity.badRequest().body(new Message("ID de pedido inválido"));
		}
		
		try {
			List<GeolocalizacaoDTO> res = service.buscarGeolocalizacao(idPedido);
			
			if (res == null) {
				return ResponseEntity.status(400).body(new Message("Pedido não encontrado. Informe um pedido válido"));
			}
			
			return ResponseEntity.ok(res);
		} catch (Exception ex) {
			return ResponseEntity.status(500).body(new Message(ex.getMessage()));
		}
	}
	
	@ApiOperation(value = "Cadastra uma nova geolocalização", notes = "Cadastra uma nova geolocalização")
	@ApiResponses( value = {
			@ApiResponse(code = 201, message = "Geolocalização cadastrada com sucesso"),
			@ApiResponse(code = 400, message = "Todos os campos são obrigatórios"),
			@ApiResponse(code = 400, message = "Pedido não encontrado"),
			@ApiResponse(code = 400, message = "Entregador não encontrado"),
	})
	@PostMapping("/geolocalizacao")
	public ResponseEntity<?> receberGeolocalizacaoEntregador(@RequestBody Geolocalizacao geo){
		try {		
			if (geo.getPedido() == null || geo.getPedido().getId() == null || geo.getEntregador() == null || geo.getEntregador().getId() == null) {
				return ResponseEntity.status(400).body(new Message("Todos os campos são obrigatórios. Ex.: \"coordenadas\": String, \"momento\": Timestamp, \"pedido\": { \"id\": integer }, \"entregador\": { \"id\": integer }"));
			}
			
			Geolocalizacao res = service.inserirGeolocalizacao(geo);
			
			if (res == null) {
				return ResponseEntity.status(400).body(new Message("Campo(s) inválido(s)."));
			}
			
			return ResponseEntity.ok(res);			
		} catch (Exception ex) {
			return ResponseEntity.status(400).body(new Message(ex.getMessage()));
		}
	}
}
