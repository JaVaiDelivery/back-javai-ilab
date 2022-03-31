package br.com.javai.projeto.dto;

import java.sql.Timestamp;
import java.util.Date;

public class GeolocalizacaoDTO {
	private Integer id;

	private Timestamp momento;
	
	private String coordenadas;
	
	private Integer idPedido;
	
	private Integer idEntregador;
	
	public GeolocalizacaoDTO(Integer id, Date momento, String coordenadas, 
			Integer idPedido, Integer idEntregador) {
		super();
		this.id = id;
		this.momento = (Timestamp)momento;
		this.coordenadas = coordenadas;
		this.idPedido = idPedido;
		this.idEntregador = idEntregador;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getMomento() {
		return momento;
	}

	public void setMomento(Timestamp momento) {
		this.momento = momento;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdEntregador() {
		return idEntregador;
	}

	public void setIdEntregador(Integer idEntregador) {
		this.idEntregador = idEntregador;
	}
}
