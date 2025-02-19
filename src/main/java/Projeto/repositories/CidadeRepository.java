package Projeto.repositories;

import Projeto.domain.Categoria;
import Projeto.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository <Cidade, Integer> {
        @Transactional(readOnly = true)
        @Query("Select obj from Cidade obj where obj.estado.id = :estadoId order by obj.nome")
        public List<Cidade> findCidades(@Param("estadoId") Integer estado_id);
}
