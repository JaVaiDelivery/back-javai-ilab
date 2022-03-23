package br.com.javai.projeto.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.javai.projeto.model.Entregador;
import br.com.javai.projeto.model.Pedido;
public interface PedidoDAO extends CrudRepository<Pedido, Integer>{
	
	@Query("SELECT  new br.com.javai.projeto.model.Pedido"
			+ "(pedido.id,"
			+ " pedido.data,"
			+ " pedido.valor,"
			+ " pedido.status,"
			+ " pedido.idCliente,"
			+ " pedido.idEntregador) "
			+ "FROM Pedido as pedido WHERE pedido.status = 'Em aberto'")
	public List<Pedido> recuperarPedidosEmAberto();

	@Query("SELECT  new br.com.javai.projeto.model.Pedido"
			+ "(pedido.id,"
			+ " pedido.data,"
			+ " pedido.valor,"
			+ " pedido.status,"
			+ " pedido.idCliente,"
			+ " pedido.idEntregador) "
			+ "FROM Pedido as pedido WHERE pedido.id = :id")
	public List<Pedido> recuperarPedidoEspecifico(@Param("id") Integer id);
	
	
	@Query("UPDATE Pedido as pedido"
			+ " SET pedido.idEntregador = '%idEntregador',"
			+ " pedido.status = 'Em progresso'"
			+ " WHERE pedido.id = '%id'")
	public boolean atribuirEntregadorEMudarStatus(@Param("IdEntregador") Integer idEntregador, @Param("id") Integer id);
	
	
	@Query("UPDATE Pedido as pedido"
			+ " SET pedido.idEntregador = null,"
			+ "pedido.status = 'Em aberto'"
			+ "WHERE pedido.id = :id")
	public boolean removerEntregadorEMudarStatus(@Param("id") Integer id);
	
	
	
	
	
//	@Param("id") Integer id, @Param("entregador") Entregador entregador
	
}
