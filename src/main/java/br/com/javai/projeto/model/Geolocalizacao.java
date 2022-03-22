package br.com.javai.projeto.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "geolocalizacao")
public class Geolocalizacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "momento")
	private Timestamp momento;
	
	@Column(name = "coordenadas", columnDefinition = "text")
	private String coordenadas;
	
	@Column(name = "id_pedido", nullable = false)
	private Integer idPedido;
	
	
	public Geolocalizacao() {
		
	}

	public Geolocalizacao(Integer id, Timestamp momento, String coordenadas, Integer idPedido) {
		super();
		this.id = id;
		this.momento = momento;
		this.coordenadas = coordenadas;
		this.idPedido = idPedido;
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
}
