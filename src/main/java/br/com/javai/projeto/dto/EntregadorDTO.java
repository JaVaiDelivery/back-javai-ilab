package br.com.javai.projeto.dto;


import java.util.List;

import br.com.javai.projeto.model.Entregador;
import br.com.javai.projeto.model.Pedido;

public class EntregadorDTO {

	private String nome;
	private String email;
	private List<Pedido> listDePedidos;	
	
	public EntregadorDTO(String nome, String email, List<Pedido> listDePedidos) {
		super();
		this.nome = nome;
		this.email = email;
		this.listDePedidos = listDePedidos;
	}
	
	
	public EntregadorDTO(Entregador entregador) {
		super();
		this.nome = entregador.getNome();
		this.email = entregador.getEmail();
		this.listDePedidos = entregador.getListaDePedidos();
	}
	
	
	public static EntregadorDTO fromEntregador(Entregador entregador) {
		return new EntregadorDTO(entregador.getNome(), entregador.getEmail(), entregador.getListaDePedidos());
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
	public List<Pedido> getListDePedidos() {
		return listDePedidos;
	}
	public void setListDePedidos(List<Pedido> listDePedidos) {
		this.listDePedidos = listDePedidos;
	}
}
