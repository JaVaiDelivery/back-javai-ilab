package br.com.javai.projeto.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "id_pedido" )
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "num_entregador")
	private Entregador entregador;

	public Geolocalizacao() {
		
	}
	
	
	public Geolocalizacao(Integer id, Timestamp momento, String coordenadas, Pedido pedido, Entregador entregador) {
		super();
		this.id = id;
		this.momento = momento;
		this.coordenadas = coordenadas;
		this.pedido = pedido;
		this.entregador = entregador;
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}
	
	
	
	
}
