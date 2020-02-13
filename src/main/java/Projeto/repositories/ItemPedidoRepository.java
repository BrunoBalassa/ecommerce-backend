package Projeto.repositories;

import Projeto.domain.Categoria;
import Projeto.domain.ItemPedido;
import Projeto.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository <ItemPedido, Integer> {


}
