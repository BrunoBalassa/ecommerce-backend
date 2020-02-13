package Projeto.repositories;

import Projeto.domain.Categoria;
import Projeto.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository <Estado, Integer> {


}
