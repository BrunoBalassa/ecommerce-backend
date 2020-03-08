package Projeto.domain.enums;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String descicao;

  private Perfil(int cod, String descicao) {
        this.cod = cod;
        this.descicao = descicao;
    }

    public int getCod() {
        return cod;
    }



    public String getDescicao() {
        return descicao;
    }

    public static Perfil toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        else{
            for(Perfil x : Perfil.values()){
                if(cod.equals(x.getCod())){
                    return x;
                }
            }
            throw new IllegalArgumentException("Id invalido: "+ cod);
        }
    }
}


