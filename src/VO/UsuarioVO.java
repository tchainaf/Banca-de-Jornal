package VO;

public class UsuarioVO extends PadraoVO {
    private String nome;
    private String senha;
    private String tipoAcesso;
    private boolean admin;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getTipoAcesso(){
        if(isAdmin())
            return "Administrador";
        return "Comum";
    }
}
