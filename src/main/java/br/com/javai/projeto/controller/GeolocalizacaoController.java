package br.com.javai.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dao.GeolocalizacaoDAO;
import br.com.javai.projeto.model.Geolocalizacao;
import br.com.javai.projeto.util.Message;

@RestController
@CrossOrigin("*")
public class GeolocalizacaoController {
	
	@Autowired
	private GeolocalizacaoDAO dao;
	
	@PostMapping("/geolocalizacao")
	public ResponseEntity<?> receberGeolocalizacaoEntregador(@RequestBody Geolocalizacao geo){
		
		try {			
			if (geo.getPedido() == null || geo.getPedido().getId() == null || geo.getEntregador() == null || geo.getEntregador().getId() == null) {
				return ResponseEntity.status(400).build();
			}
			
			Geolocalizacao nova = dao.save(geo);
			
			return ResponseEntity.ok(nova);
			
		} catch (Exception ex) {
			
			return ResponseEntity.status(400).body(new Message(ex.getMessage()));
		}
	}
}
