package Projeto.service;

import Projeto.domain.Categoria;
import Projeto.domain.Produto;
import Projeto.repositories.CategoriaRepository;
import Projeto.repositories.ProdutoRepository;
import Projeto.service.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository Produto;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id){
          Optional<Produto> obj = Produto.findById(id);
          return obj.orElseThrow(() ->
                  new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo " +
                          Produto.class.getName()));
    }
    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return Produto.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
