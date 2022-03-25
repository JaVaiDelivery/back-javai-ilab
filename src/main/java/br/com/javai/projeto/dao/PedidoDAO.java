package br.com.javai.projeto.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.javai.projeto.model.Pedido;

public interface PedidoDAO extends CrudRepository<Pedido, Integer>{
	
	@Query(value = "SELECT * FROM pedido WHERE pedido.status = 'Em aberto'", nativeQuery = true)
	public List<Pedido> recuperarPedidosEmAberto();
	
	@Transactional
	@Modifying
	@Query("UPDATE Pedido as pedido"
			+ " SET pedido.entregador.id = :idEntregador,"
			+ " pedido.status = 'Em progresso'"
			+ " WHERE pedido.id = :id")
	public void atribuirEntregadorEMudarStatus(@Param("idEntregador") Integer idEntregador, @Param("id") Integer id);
	
	@Query("UPDATE Pedido as pedido"
			+ " SET pedido.entregador = null,"
			+ "pedido.status = 'Em aberto'"
			+ "WHERE pedido.id = :id")
	public boolean removerEntregadorEMudarStatus(@Param("id") Integer id);
}