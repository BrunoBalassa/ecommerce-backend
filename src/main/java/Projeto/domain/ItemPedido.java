package Projeto.domain;

import java.io.Serializable;

import java.text.NumberFormat;
import java.util.Locale;



import javax.persistence.EmbeddedId;

import javax.persistence.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity

public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;



    @JsonIgnore

    @EmbeddedId

    private ItemPedidoPK id = new ItemPedidoPK();



    private Double desconto;

    private Integer quantidade;

    private Double preco;



    public ItemPedido() {

    }



    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {

        super();

        id.setPedido(pedido);

        id.setProduto(produto);

        this.desconto = desconto;

        this.quantidade = quantidade;

        this.preco = preco;

    }



    public double getSubTotal() {

        return (preco - desconto) * quantidade;

    }



    @JsonIgnore

    public Pedido getPedido() {

        return id.getPedido();

    }



    public void setPedido(Pedido pedido) {

        id.setPedido(pedido);

    }



    public Produto getProduto() {

        return id.getProduto();

    }



    public void setProduto(Produto produto) {

        id.setProduto(produto);

    }



    public ItemPedidoPK getId() {

        return id;

    }



    public void setId(ItemPedidoPK id) {

        this.id = id;

    }



    public Double getDesconto() {

        return desconto;

    }



    public void setDesconto(Double desconto) {

        this.desconto = desconto;

    }



    public Integer getQuantidade() {

        return quantidade;

    }



    public void setQuantidade(Integer quantidade) {

        this.quantidade = quantidade;

    }



    public Double getPreco() {

        return preco;

    }



    public void setPreco(Double preco) {

        this.preco = preco;

    }



    @Override

    public int hashCode() {

        final int prime = 31;

        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;

    }



    @Override

    public boolean equals(Object obj) {

        if (this == obj)

            return true;

        if (obj == null)

            return false;

        if (getClass() != obj.getClass())

            return false;

        ItemPedido other = (ItemPedido) obj;

        if (id == null) {

            if (other.id != null)

                return false;

        } else if (!id.equals(other.id))

            return false;

        return true;

    }


    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        StringBuffer sb = new StringBuffer();
        sb.append( sb.append(getProduto().getNome()));
        sb.append(", Qte: ");
        sb.append(getQuantidade());
        sb.append(", Preco unitario: " );
        sb.append(formatter.format(getPreco()));
        sb.append(", Subtotal: ");
        sb.append(formatter.format(getSubTotal()));
        sb.append("\n");

        return sb.toString();
    }
}