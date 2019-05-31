package VO;

public class PagamentoVO {
    private String sigla;
    private String descricao;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PagamentoVO(String sg, String desc) {
        sigla = sg;
        descricao = desc;
    }

    @Override
    public String toString(){
        return getDescricao();
    }
}
