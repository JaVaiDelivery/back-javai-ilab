package br.com.javai.projeto.util;

import java.util.Optional;

public enum StatusDoPedido {
	
	EM_ABERTO(1),
	EM_ROTA(2),
	CONCLUIDO(3),
	CANCELADO(4);
	
	
	private int numeroStatus;
	
	StatusDoPedido(){
		
	}
	
	StatusDoPedido(int numero) {
		this.numeroStatus = numero;
	}
	

	public int getNumeroStatus() {
		return numeroStatus;
	}

	public void setNumeroStatus(int numeroStatus) {
		this.numeroStatus = numeroStatus;
	}
	
	public static StatusDoPedido getStatusDoPedidoValueFromInt(int i) {
	     for (StatusDoPedido status : StatusDoPedido.values()) {
	         if (status.getNumeroStatus() == i) {
	             return status;
	         }
	     }
	     // throw an IllegalArgumentException or return null
	     throw new IllegalArgumentException("the given number doesn't match any Status.");
	 }
}
