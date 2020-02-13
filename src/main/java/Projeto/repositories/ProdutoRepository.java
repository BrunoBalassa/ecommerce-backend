package Projeto.repositories;

import Projeto.domain.Categoria;
import Projeto.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Integer> {


}
