package fornecedor;

import DAO.FornecedorDAO;
import VO.FornecedorVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

public class Controller {
    FornecedorDAO dao = new FornecedorDAO();

    @FXML
    Button btnCancelar, btnSalvar, btnCadastrar, btnAlterar, btnDeletar;

    @FXML
    TextField txtNome, txtCNPJ, txtEndereco, txtTelefone, txtPesquisa;

    @FXML
    public void initialize() {
        HabilitaCampos(false);
    }

    public void novoForn(ActionEvent actionEvent) {
        LimpaCampos();
        HabilitaCampos(true);
    }

    public void altForn(ActionEvent actionEvent) {
        HabilitaCampos(true);
    }

    public void delForn(ActionEvent actionEvent) {
        if(!MessageBox(Alert.AlertType.WARNING, "Tem certeza que quer deletar esse registro?", true))
            return;

        if(dao.Deletar(0)) {//txtId.getText()
            LimpaCampos();
            HabilitaCampos(false);
            MessageBox(Alert.AlertType.INFORMATION, "Os dados do fornecedor foram deletados com sucesso!", false);
        }
        else {
            MessageBox(Alert.AlertType.ERROR, "Erro ao deletar os dados do fornecedor! Tente novamente.", false);
        }
    }

    public void irParaFornecedor(ActionEvent actionEvent) {
        HabilitaCampos(false);
        FornecedorVO forn = (FornecedorVO) dao.Ler(Integer.getInteger(txtPesquisa.getText()));

        txtNome.setText(forn.getNome());
        txtCNPJ.setText(forn.getCNPJ());
        txtEndereco.setText(forn.getEndereco());
        txtTelefone.setText(forn.getTelefone());
    }

    public void cancelarForn(ActionEvent actionEvent) {
        try {
            if(!MessageBox(Alert.AlertType.WARNING, "Tem certeza? Você vai perder as alterações não salvas!", true))
                return;

            HabilitaCampos(false);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirForn(ActionEvent actionEvent) {
        try {
            if(!ValidaCampos()) {
                MessageBox(Alert.AlertType.ERROR, "Preencha todos os campos!", false);
                return;
            }

            FornecedorVO forn = new FornecedorVO();
            //forn.setId(txtId.getText());
            forn.setNome(txtNome.getText());
            forn.setCNPJ(txtCNPJ.getText());
            forn.setEndereco(txtEndereco.getText());
            forn.setTelefone(txtTelefone.getText());

            boolean sucesso = false;

            if(true)//Cadastrar ou alterar????
                sucesso = dao.Inserir(forn);
            else
                sucesso = dao.Alterar(forn);

            if(sucesso){
                HabilitaCampos(false);
                MessageBox(Alert.AlertType.INFORMATION, "Os dados do fornecedor foram salvos com sucesso!", false);
                LimpaCampos();
            } else {
                MessageBox(Alert.AlertType.ERROR, "Erro ao salvar os dados do fornecedor! Tente novamente.", false);
            }

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
            txtCNPJ.setDisable(false);
            txtEndereco.setDisable(false);
            txtTelefone.setDisable(false);
            txtPesquisa.setDisable(true);
        } else {
            btnCadastrar.setDisable(false);
            btnAlterar.setDisable(false);
            btnDeletar.setDisable(false);
            btnCancelar.setDisable(true);
            btnSalvar.setDisable(true);

            txtNome.setDisable(true);
            txtCNPJ.setDisable(true);
            txtEndereco.setDisable(true);
            txtTelefone.setDisable(true);
            txtPesquisa.setDisable(false);
        }
    }

    private boolean ValidaCampos () {
        if(txtNome.getLength() < 4)
            return false;
        if(txtCNPJ.getLength() != 14)
            return false;
        if(txtEndereco.getLength() < 8)
            return false;
        if(txtTelefone.getLength() < 8)
            return false;
        return true;
    }

    private void LimpaCampos () {
        txtNome.setText(null);
        txtCNPJ.setText(null);
        txtEndereco.setText(null);
        txtTelefone.setText(null);
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
