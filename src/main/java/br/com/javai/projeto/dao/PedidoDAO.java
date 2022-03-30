package br.com.javai.projeto.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.javai.projeto.model.Pedido;
import br.com.javai.projeto.util.StatusDoPedido;

public interface PedidoDAO extends CrudRepository<Pedido, Integer>{
	
	@Query(value = "SELECT * FROM pedido WHERE pedido.status = 'Em aberto'", nativeQuery = true)
	public List<Pedido> recuperarPedidosEmAberto();
	
	@Transactional
	@Modifying
	@Query("UPDATE Pedido as pedido"
			+ " SET pedido.entregador.id = :idEntregador"
			+ " WHERE pedido.id = :id")
	public void atribuirEntregador(@Param("idEntregador") Integer idEntregador, @Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query("UPDATE Pedido as pedido"
			+ " SET pedido.status = :status"
			+ " WHERE pedido.id = :id")
	public void mudarStatus(@Param("status") StatusDoPedido status, @Param("id") Integer id);
	
	@Transactional
	@Modifying	
	@Query("UPDATE Pedido as pedido"
			+ " SET pedido.entregador = null"
			+ " WHERE pedido.id = :id")
	public boolean removerEntregador(@Param("id") Integer id);
}