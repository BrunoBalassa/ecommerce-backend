package Projeto.domain.enums;

public enum TipoCliente {


    PESSOAFISICA(1,"Pessoa física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int cod;
    private String descicao;

    TipoCliente(int cod, String descicao) {
        this.cod = cod;
        this.descicao = descicao;
    }

    public int getCod() {
        return cod;
    }



    public String getDescicao() {
        return descicao;
    }

    public static TipoCliente toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        else{
            for(TipoCliente x : TipoCliente.values()){
                if(cod.equals(x.getCod())){
                    return x;
                }
            }
            throw new IllegalArgumentException("Id invalido: "+ cod);
        }
    }
}
