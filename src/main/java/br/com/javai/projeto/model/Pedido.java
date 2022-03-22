package br.com.javai.projeto.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name = "id_cliente", nullable = false)
	private Integer idCliente;
	
	@Column(name = "id_entregador")
	private Integer idEntregador;
	

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

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdEntregador() {
		return idEntregador;
	}

	public void setIdEntregador(Integer idEntregador) {
		this.idEntregador = idEntregador;
	}
}
