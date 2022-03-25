package br.com.javai.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dao.GeolocalizacaoDAO;
import br.com.javai.projeto.dao.PedidoDAO;
import br.com.javai.projeto.model.Geolocalizacao;

@RestController
public class GeolocalizacaoController {

	@Autowired
	private GeolocalizacaoDAO dao;
	
	@Autowired
	private PedidoDAO pedidoDao;
	
	@PostMapping("/geolocalizacao")
	public Geolocalizacao receberGeolocalizacaoEntregador(@RequestBody Geolocalizacao geo){
		try {
			if (geo.getEntregador() != null && geo.getPedido() != null) {	
				pedidoDao.atribuirEntregadorEMudarStatus(geo.getEntregador().getId(), geo.getPedido().getId());
				
				return dao.save(geo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
