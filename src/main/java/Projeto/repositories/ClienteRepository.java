package Projeto.repositories;

import Projeto.domain.Cidade;
import Projeto.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Integer> {


}
