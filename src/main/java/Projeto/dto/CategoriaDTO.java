package Projeto.dto;

import Projeto.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;


    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Length(min = 5,max = 80,message = "Min 5 maximo 80")
    private String nome;

    public CategoriaDTO(){}

    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }
}
