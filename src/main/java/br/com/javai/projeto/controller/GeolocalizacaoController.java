package br.com.javai.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dao.EntregadorDAO;
import br.com.javai.projeto.model.Geolocalizacao;

@RestController
public class GeolocalizacaoController {

	@Autowired
	private EntregadorDAO dao;
	
	@GetMapping("/geolocalizacao/{idEntregador}")
	public List<Geolocalizacao> receberGeolocalizacaoEntregador(@PathVariable Integer idEntregador){
		List<Geolocalizacao> geos = dao.findAllById(idEntregador);
		
		return geos;
	}
	
}
