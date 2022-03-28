package br.com.javai.projeto.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name  ="id")
	private Integer id;
	
	@Column(name = "data")
	private LocalDate data;
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@Column(name = "status", length = 15, nullable = false)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@JsonIgnoreProperties("listaDePedidos")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "id_entregador")
	@JsonIgnoreProperties("listaDePedidos")
	private Entregador entregador;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"pedido", "entregador"})
	private List<Geolocalizacao> tracking;
	
	public Pedido() {
		
	}

	public Pedido(Integer id, LocalDate data, Double valor, String status, Cliente cliente, Entregador entregador,
			List<Geolocalizacao> tracking) {
		super();
		this.id = id;
		this.data = data;
		this.status = status;
		this.cliente = cliente;
		this.entregador = entregador;
		this.tracking = tracking;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	public List<Geolocalizacao> getTracking() {
		return tracking;
	}

	public void setTracking(List<Geolocalizacao> tracking) {
		this.tracking = tracking;
	}
	
	
	
}
