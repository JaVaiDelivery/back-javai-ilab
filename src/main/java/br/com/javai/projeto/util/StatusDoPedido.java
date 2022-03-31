package br.com.javai.projeto.util;

public enum StatusDoPedido {
	
	EM_ABERTO(0),
	EM_ROTA(1),
	CONCLUIDO(2),
	CANCELADO(3);
	
	
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
	         }else {
	        	 return null;
	         }
	     }
	     // throw an IllegalArgumentException or return null
	     throw new IllegalArgumentException("the given number doesn't match any Status.");
	     
	     
	 }
}
