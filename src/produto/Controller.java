package produto;

import DAO.*;
import VO.*;
import util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    ProdutoDAO dao = new ProdutoDAO();

    @FXML
    Button btnCancelar, btnSalvar, btnCadastrar, btnAlterar, btnDeletar;

    @FXML
    TextField txtCodigo, txtDescricao, txtPreco, txtPesquisa;

    @FXML
    ComboBox cbxFornecedor;

    @FXML
    public void initialize() {
        HabilitaCampos(false);
    }

    public void novoProduto(ActionEvent actionEvent) {
        LimpaCampos();
        HabilitaCampos(true);
    }

    public void altProduto(ActionEvent actionEvent) {
        HabilitaCampos(true);
    }

    public void delProduto(ActionEvent actionEvent) {
        if (!Show.MessageBox(Alert.AlertType.WARNING, "Tem certeza que quer deletar esse registro?", true))
            return;

        if (dao.Deletar(Integer.getInteger(txtCodigo.getText()))) {
            LimpaCampos();
            HabilitaCampos(false);
            Show.MessageBox(Alert.AlertType.INFORMATION, "Os dados do produto foram deletados com sucesso!", false);
        } else {
            Show.MessageBox(Alert.AlertType.ERROR, "Erro ao deletar os dados do produto! Tente novamente.", false);
        }
    }

    public void irParaProduto(ActionEvent actionEvent) {
        HabilitaCampos(false);
        ProdutoVO prod = (ProdutoVO) dao.Ler(Integer.getInteger(txtPesquisa.getText()));

        txtCodigo.setText(String.valueOf(prod.getCodigo()));
        txtDescricao.setText(prod.getDescricao());
        cbxFornecedor.setValue(prod.getFornecedor());
        txtPreco.setText(String.valueOf(prod.getPreco()));
    }

    public void cancelarProduto(ActionEvent actionEvent) {
        try {
            if (!Show.MessageBox(Alert.AlertType.WARNING, "Tem certeza? Você vai perder as alterações não salvas!", true))
                return;

            HabilitaCampos(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirProduto(ActionEvent actionEvent) {
        try {
            if (!ValidaCampos()) {
                Show.MessageBox(Alert.AlertType.ERROR, "Preencha todos os campos!", false);
                return;
            }

            ProdutoVO prod = new ProdutoVO();
            prod.setCodigo(Integer.getInteger(txtCodigo.getText()));
            prod.setDescricao(txtDescricao.getText());
            prod.setFornecedor(0); //cbxFornecedor.getValue()
            prod.setPreco(Double.valueOf(txtPreco.getText()));

            boolean sucesso = false;

            if (prod.getCodigo() == 0)
                sucesso = dao.Inserir(prod);
            else
                sucesso = dao.Alterar(prod);

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

            txtDescricao.setDisable(false);
            cbxFornecedor.setDisable(false);
            txtPreco.setDisable(false);
            txtPesquisa.setDisable(true);
        } else {
            btnCadastrar.setDisable(false);
            btnAlterar.setDisable(false);
            btnDeletar.setDisable(false);
            btnCancelar.setDisable(true);
            btnSalvar.setDisable(true);

            txtDescricao.setDisable(true);
            cbxFornecedor.setDisable(true);
            txtPreco.setDisable(true);
            txtPesquisa.setDisable(false);
        }
    }

    private boolean ValidaCampos() {
        if (txtDescricao.getLength() < 10)
            return false;
        if (cbxFornecedor.getValue() == null)
            return false;
        if (txtPreco.getLength() < 3)
            return false;
        return true;
    }

    private void LimpaCampos() {
        txtCodigo.setText(null);
        txtDescricao.setText(null);
        cbxFornecedor.setValue(null);
        txtPreco.setText(null);
    }
}
