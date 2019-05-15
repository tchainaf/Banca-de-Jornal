package usuario;

import DAO.*;
import VO.*;
import util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    UsuarioDAO dao = new UsuarioDAO();

    @FXML
    Button btnCancelar, btnSalvar, btnCadastrar, btnAlterar, btnDeletar;

    @FXML
    TextField txtCodigo, txtNome, txtSenha, txtConfSenha, txtPesquisa;

    @FXML
    RadioButton rbtnAdmin, rbtnComum;

    @FXML
    public void initialize() {
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

        if (dao.Deletar(Integer.getInteger(txtCodigo.getText()))) {
            LimpaCampos();
            HabilitaCampos(false);
            Show.MessageBox(Alert.AlertType.INFORMATION, "Os dados do usuário foram deletados com sucesso!", false);
        } else {
            Show.MessageBox(Alert.AlertType.ERROR, "Erro ao deletar os dados do usuário! Tente novamente.", false);
        }
    }

    public void irParaUsuario(ActionEvent actionEvent) {
        HabilitaCampos(false);
        UsuarioVO user = (UsuarioVO) dao.Ler(Integer.getInteger(txtPesquisa.getText()));

        txtCodigo.setText(String.valueOf(user.getCodigo()));
        txtNome.setText(user.getNome());
        txtSenha.setText(user.getSenha());
        if (user.isAdmin())
            rbtnAdmin.setSelected(true);
        else
            rbtnComum.setSelected(true);
    }

    public void cancelarUser(ActionEvent actionEvent) {
        try {
            if (!Show.MessageBox(Alert.AlertType.WARNING, "Tem certeza? Você vai perder as alterações não salvas!", true))
                return;

            HabilitaCampos(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirUser(ActionEvent actionEvent) {
        try {
            if (!ValidaCampos()) {
                Show.MessageBox(Alert.AlertType.ERROR, "Preencha todos os campos!", false);
                return;
            }

            UsuarioVO user = new UsuarioVO();
            user.setCodigo(Integer.getInteger(txtCodigo.getText()));
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
        if (edit) {
            btnCadastrar.setDisable(true);
            btnAlterar.setDisable(true);
            btnDeletar.setDisable(true);
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
            btnAlterar.setDisable(false);
            btnDeletar.setDisable(false);
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

    private boolean ValidaCampos() {
        if (txtNome.getLength() < 4)
            return false;
        if (txtSenha.getLength() < 8)
            return false;
        if (txtConfSenha.getLength() < 8)
            return false;
        if (!rbtnAdmin.isSelected() && !rbtnComum.isSelected())
            return false;
        if (txtSenha.getText() != txtConfSenha.getText())
            return false;
        return true;
    }

    private void LimpaCampos() {
        txtNome.setText(null);
        txtSenha.setText(null);
        txtConfSenha.setText(null);
        rbtnAdmin.setSelected(false);
        rbtnComum.setSelected(false);
    }
}
