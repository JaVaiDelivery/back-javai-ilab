package br.com.javai.projeto.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "entregador")
public class Entregador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome", length = 120, nullable = false)
	private String nome;
	
	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;
	
	@Column(name = "telefone", length = 15)
	private String telefone;
	
	@Column(name = "senha", columnDefinition = "text", nullable = false)
	private String senha;
	
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"entregador", "cliente"})
	private List<Pedido> listaDePedidos;

	
	@OneToMany(mappedBy = "entregador", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"entregador", "pedido"})
	private List<Geolocalizacao> listaDeGeolocalizacao;
	
	public Entregador() {
		
	}
	

	public Entregador(Integer id, String nome, String email, String telefone, String senha, List<Pedido> listaDePedidos,
			List<Geolocalizacao> listaDeGeolocalizacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.listaDePedidos = listaDePedidos;
		this.listaDeGeolocalizacao = listaDeGeolocalizacao;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public List<Pedido> getListaDePedidos() {
		return listaDePedidos;
	}


	public void setListaDePedidos(List<Pedido> listaDePedidos) {
		this.listaDePedidos = listaDePedidos;
	}


	public List<Geolocalizacao> getListaDeGeolocalizacao() {
		return listaDeGeolocalizacao;
	}


	public void setListaDeGeolocalizacao(List<Geolocalizacao> listaDeGeolocalizacao) {
		this.listaDeGeolocalizacao = listaDeGeolocalizacao;
	}
	
	
}
