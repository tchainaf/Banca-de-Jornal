package usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class Controller {
    @FXML
    Button btnCancelar, btnSalvar, btnCadastrar, btnAlterar, btnDeletar;

    @FXML
    TextField txtNome, txtSenha, txtConfSenha, txtPesquisa;

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
        if(!MessageBox(Alert.AlertType.WARNING, "Tem certeza que quer deletar esse registro?", true))
            return;
        LimpaCampos();
        HabilitaCampos(false);
    }

    public void irParaUsuario(ActionEvent actionEvent) {
        HabilitaCampos(false);
    }

    public void cancelarUser(ActionEvent actionEvent) {
        try {
            if(!MessageBox(Alert.AlertType.WARNING, "Tem certeza? Você vai perder as alterações não salvas!", true))
                return;

            HabilitaCampos(false);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirUser(ActionEvent actionEvent) {
        try {
            if(!ValidaCampos()) {
                MessageBox(Alert.AlertType.ERROR, "Preencha todos os campos!", false);
                return;
            }

            HabilitaCampos(false);
            MessageBox(Alert.AlertType.INFORMATION, "Os dados do fornecedor foram salvos com sucesso!", false);
            LimpaCampos();
        } catch(Exception e) {
            MessageBox(Alert.AlertType.ERROR, "Erro ao salvar os dados do fornecedor!", false);
            e.printStackTrace();
        }
    }

    private void HabilitaCampos (boolean edit){
        if(edit) {
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

    private boolean ValidaCampos () {
        if(txtNome.getLength() < 4)
            return false;
        if(txtSenha.getLength() != 14)
            return false;
        if(txtConfSenha.getLength() < 8)
            return false;
        if(!rbtnAdmin.isSelected() && !rbtnComum.isSelected())
            return false;
        return true;
    }

    private void LimpaCampos () {
        txtNome.setText(null);
        txtSenha.setText(null);
        txtConfSenha.setText(null);
        rbtnAdmin.setSelected(false);
        rbtnComum.setSelected(false);
    }

    private boolean MessageBox (Alert.AlertType tipo, String mensagem, boolean botoes){
        Alert msg;
        if(!botoes) {
            msg = new Alert(tipo, mensagem);
            msg.setHeaderText(null);
            msg.show();
        } else {
            msg = new Alert(tipo, mensagem, ButtonType.YES, ButtonType.NO);
            msg.setHeaderText(null);
            Optional<ButtonType> result = msg.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.YES)
                return true;
        }
        return false;
    }
}
