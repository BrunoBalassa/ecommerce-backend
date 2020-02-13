package Projeto.repositories;

import Projeto.domain.Categoria;
import Projeto.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Integer> {


}
