package br.com.javai.projeto.util;

public enum StatusDoPedido {
	
	EM_ABERTO(1),
	EM_ROTA(2),
	CONCLUIDO(3),
	CANCELADO(4);
	
	
	private int numeroStatus;
	
	
	StatusDoPedido(int numero) {
		this.numeroStatus = numero;
	}
	

	public int getNumeroStatus() {
		return numeroStatus;
	}

	public void setNumeroStatus(int numeroStatus) {
		this.numeroStatus = numeroStatus;
	}
}
