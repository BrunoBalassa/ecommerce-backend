package Projeto.dto;

import Projeto.service.validation.ClienteInsert;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 5,max = 120,message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @CPF
    private String cpfOuCnpj;


    private Integer tipo;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private  String senha;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String numero;

    private String complemento;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String cep;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String telefone1;

    private String telefone2;
    private String telefone3;

    private Integer cidadeId;

    public ClienteNewDTO(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
