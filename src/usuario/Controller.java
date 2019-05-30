package usuario;

import DAO.UsuarioDAO;
import VO.UsuarioVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import util.Show;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    UsuarioDAO dao = new UsuarioDAO();

    @FXML Button btnCancelar, btnSalvar, btnCadastrar, btnAlterar, btnDeletar;
    @FXML TextField txtCodigo, txtNome, txtPesquisa;
    @FXML PasswordField txtSenha, txtConfSenha;
    @FXML RadioButton rbtnAdmin, rbtnComum;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HabilitaCampos(false);
    }

    public void novoUser(ActionEvent actionEvent) {
        LimpaCampos();
        HabilitaCampos(true);
    }

    public void altUser(ActionEvent actionEvent) {
        HabilitaCampos(true);
    }

    public void delUser(ActionEvent actionEvent) {
        if (!Show.MessageBox(Alert.AlertType.WARNING, "Tem certeza que quer deletar esse registro?", true))
            return;

        if (dao.Deletar(Integer.valueOf(txtCodigo.getText()))) {
            LimpaCampos();
            HabilitaCampos(false);
            Show.MessageBox(Alert.AlertType.INFORMATION, "Os dados do usuário foram deletados com sucesso!", false);
        } else {
            Show.MessageBox(Alert.AlertType.ERROR, "Erro ao deletar os dados do usuário! Tente novamente.", false);
        }
    }

    public void irParaUsuario(ActionEvent actionEvent) {
        HabilitaCampos(false);
        UsuarioVO user = (UsuarioVO) dao.Ler(Integer.valueOf(txtPesquisa.getText()));

        if(user == null){
            Show.MessageBox(Alert.AlertType.WARNING, "Esse usuário não foi encontrado!", false);
            return;
        }

        txtCodigo.setText(String.valueOf(user.getCodigo()));
        txtNome.setText(user.getNome());
        txtSenha.setText(user.getSenha());
        if (user.isAdmin()) {
            rbtnAdmin.setSelected(true);
            rbtnComum.setSelected(false);
        }
        else {
            rbtnAdmin.setSelected(false);
            rbtnComum.setSelected(true);
        }

        btnAlterar.setDisable(false);
        btnDeletar.setDisable(false);
    }

    public void cancelarUser(ActionEvent actionEvent) {
        try {
            if (!Show.MessageBox(Alert.AlertType.WARNING, "Tem certeza? Você vai perder as alterações não salvas!", true))
                return;

            if(txtCodigo.getText() == null)
                LimpaCampos();
            else{
                UsuarioVO user = (UsuarioVO) dao.Ler(Integer.valueOf(txtPesquisa.getText()));

                txtCodigo.setText(String.valueOf(user.getCodigo()));
                txtNome.setText(user.getNome());
                txtSenha.setText(user.getSenha());
                if (user.isAdmin()) {
                    rbtnAdmin.setSelected(true);
                    rbtnComum.setSelected(false);
                }
                else {
                    rbtnAdmin.setSelected(false);
                    rbtnComum.setSelected(true);
                }
            }
            HabilitaCampos(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirUser(ActionEvent actionEvent) {
        try {
            String msg = ValidaCampos();
            if (msg != null) {
                Show.MessageBox(Alert.AlertType.ERROR, msg, false);
                return;
            }

            UsuarioVO user = new UsuarioVO();
            user.setCodigo((txtCodigo.getText() == null || txtCodigo.getText().isEmpty()) ? 0 : Integer.valueOf(txtCodigo.getText().trim()));
            user.setNome(txtNome.getText());
            user.setSenha(txtSenha.getText());
            user.setAdmin(rbtnAdmin.isSelected());

            boolean sucesso = false;

            if (user.getCodigo() == 0)
                sucesso = dao.Inserir(user);
            else
                sucesso = dao.Alterar(user);

            if (sucesso) {
                HabilitaCampos(false);
                Show.MessageBox(Alert.AlertType.INFORMATION, "Os dados do usuário foram salvos com sucesso!", false);
                LimpaCampos();
            } else {
                Show.MessageBox(Alert.AlertType.ERROR, "Erro ao salvar os dados do usuário! Tente novamente.", false);
            }

        } catch (Exception e) {
            Show.MessageBox(Alert.AlertType.ERROR, "Erro ao salvar os dados do usuário!", false);
            e.printStackTrace();
        }
    }

    private void HabilitaCampos(boolean edit) {
        btnAlterar.setDisable(true);
        btnDeletar.setDisable(true);

        if (edit) {
            btnCadastrar.setDisable(true);
            btnCancelar.setDisable(false);
            btnSalvar.setDisable(false);

            txtNome.setDisable(false);
            txtSenha.setDisable(false);
            txtConfSenha.setDisable(false);
            txtPesquisa.setDisable(true);
            rbtnAdmin.setDisable(false);
            rbtnComum.setDisable(false);
        } else {
            btnCadastrar.setDisable(false);
            btnCancelar.setDisable(true);
            btnSalvar.setDisable(true);

            txtNome.setDisable(true);
            txtSenha.setDisable(true);
            txtConfSenha.setDisable(true);
            txtPesquisa.setDisable(false);
            rbtnAdmin.setDisable(true);
            rbtnComum.setDisable(true);
        }
    }

    private String ValidaCampos() {
        if (txtNome.getLength() < 4)
            return "O nome deve ter no mínimo 4 caracteres!";
        if (txtSenha.getLength() < 6)
            return "A senha deve ter no mínimo 6 caracteres!";
        if (txtConfSenha.getLength() < 6)
            return "A confirmação de senha deve ter no mínimo 6 caracteres!";
        if (!rbtnAdmin.isSelected() && !rbtnComum.isSelected())
            return "Selecione o tipo de acesso!";
        if (!txtSenha.getText().equals(txtConfSenha.getText()))
            return "A senha e a confirmação de senha devem ser iguais!";
        return null;
    }

    private void LimpaCampos() {
        txtCodigo.setText(null);
        txtNome.setText(null);
        txtSenha.setText(null);
        txtConfSenha.setText(null);
        txtPesquisa.setText(null);
        rbtnAdmin.setSelected(false);
        rbtnComum.setSelected(false);
    }

    public void changeRbtn(ActionEvent actionEvent) {
        if(rbtnAdmin.isSelected())
            rbtnComum.setSelected(false);
        else
            rbtnComum.setSelected(true);
    }
}
