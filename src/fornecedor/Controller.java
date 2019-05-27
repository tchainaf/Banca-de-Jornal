package fornecedor;

import DAO.*;
import VO.*;
import util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    FornecedorDAO dao = new FornecedorDAO();

    @FXML
    Button btnCancelar, btnSalvar, btnCadastrar, btnAlterar, btnDeletar;

    @FXML
    TextField txtCodigo, txtNome, txtCNPJ, txtEndereco, txtTelefone, txtPesquisa;

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
        if (!Show.MessageBox(Alert.AlertType.WARNING, "Tem certeza que quer deletar esse registro?", true))
            return;

        if (dao.Deletar(Integer.valueOf(txtCodigo.getText()))) {
            LimpaCampos();
            HabilitaCampos(false);
            Show.MessageBox(Alert.AlertType.INFORMATION, "Os dados do fornecedor foram deletados com sucesso!", false);
        } else {
            Show.MessageBox(Alert.AlertType.ERROR, "Erro ao deletar os dados do fornecedor! Tente novamente.", false);
        }
    }

    public void irParaFornecedor(ActionEvent actionEvent) {
        HabilitaCampos(false);
        FornecedorVO forn = (FornecedorVO) dao.Ler(Integer.valueOf(txtPesquisa.getText()));

        if(forn == null){
            Show.MessageBox(Alert.AlertType.WARNING, "Esse fornecedor não foi encontrado!", false);
            return;
        }

        txtCodigo.setText(String.valueOf(forn.getCodigo()));
        txtNome.setText(forn.getNome());
        txtCNPJ.setText(forn.getCNPJ());
        txtEndereco.setText(forn.getEndereco());
        txtTelefone.setText(forn.getTelefone());
    }

    public void cancelarForn(ActionEvent actionEvent) {
        try {
            if (!Show.MessageBox(Alert.AlertType.WARNING, "Tem certeza? Você vai perder as alterações não salvas!", true))
                return;

            if(txtCodigo.getText() == null)
                LimpaCampos();
            else{
                FornecedorVO forn = (FornecedorVO) dao.Ler(Integer.valueOf(txtCodigo.getText()));

                txtCodigo.setText(String.valueOf(forn.getCodigo()));
                txtNome.setText(forn.getNome());
                txtCNPJ.setText(forn.getCNPJ());
                txtEndereco.setText(forn.getEndereco());
                txtTelefone.setText(forn.getTelefone());
            }
            HabilitaCampos(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirForn(ActionEvent actionEvent) {
        try {
            String msg = ValidaCampos();
            if (msg != null) {
                Show.MessageBox(Alert.AlertType.ERROR, msg, false);
                return;
            }

            FornecedorVO forn = new FornecedorVO();
            forn.setCodigo(txtCodigo.getText() == null ? 0 : Integer.valueOf(txtCodigo.getText().trim()));
            forn.setNome(txtNome.getText());
            forn.setCNPJ(txtCNPJ.getText());
            forn.setEndereco(txtEndereco.getText());
            forn.setTelefone(txtTelefone.getText());

            boolean sucesso = false;

            if (forn.getCodigo() == 0)
                sucesso = dao.Inserir(forn);
            else
                sucesso = dao.Alterar(forn);

            if (sucesso) {
                HabilitaCampos(false);
                Show.MessageBox(Alert.AlertType.INFORMATION, "Os dados do fornecedor foram salvos com sucesso!", false);
                LimpaCampos();
            } else {
                Show.MessageBox(Alert.AlertType.ERROR, "Erro ao salvar os dados do fornecedor! Tente novamente.", false);
            }

        } catch (Exception e) {
            Show.MessageBox(Alert.AlertType.ERROR, "Erro ao salvar os dados do fornecedor!", false);
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

    private String ValidaCampos() {
        if (txtNome.getLength() < 5)
            return "A razão social deve ter no mínimo 5 caracteres!";
        if (txtCNPJ.getLength() != 14)
            return "O CNPJ deve ter 14 caracteres!";
        if (txtEndereco.getLength() < 8)
            return "O endereço deve ter no mínimo 8 caracteres!";
        if (txtTelefone.getLength() < 8)
            return "O telefone deve ter no mínimo 8 caracteres!";
        return null;
    }

    private void LimpaCampos() {
        txtCodigo.setText(null);
        txtNome.setText(null);
        txtCNPJ.setText(null);
        txtEndereco.setText(null);
        txtTelefone.setText(null);
    }
}
