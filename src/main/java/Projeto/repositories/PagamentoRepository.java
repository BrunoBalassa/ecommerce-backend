package Projeto.repositories;

import Projeto.domain.Pagamento;
import Projeto.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository <Pagamento, Integer> {


}
