package produto;

import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import VO.ProdutoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import util.Show;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    ProdutoDAO dao = new ProdutoDAO();

    @FXML Button btnCancelar, btnSalvar, btnCadastrar, btnAlterar, btnDeletar;
    @FXML TextField txtCodigo, txtDescricao, txtPreco, txtPesquisa;
    @FXML ComboBox cbxFornecedor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HabilitaCampos(false);

        FornecedorDAO forn = new FornecedorDAO();
        cbxFornecedor.setItems(forn.Listar(true));
        //cbxFornecedor.setCellFactory(new Pro);
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

        if (dao.Deletar(Integer.valueOf(txtCodigo.getText()))) {
            LimpaCampos();
            HabilitaCampos(false);
            Show.MessageBox(Alert.AlertType.INFORMATION, "Os dados do produto foram deletados com sucesso!", false);
        } else {
            Show.MessageBox(Alert.AlertType.ERROR, "Erro ao deletar os dados do produto! Tente novamente.", false);
        }
    }

    public void irParaProduto(ActionEvent actionEvent) {
        HabilitaCampos(false);
        ProdutoVO prod = (ProdutoVO) dao.Ler(Integer.valueOf(txtPesquisa.getText()));

        if(prod == null){
            Show.MessageBox(Alert.AlertType.WARNING, "Esse produto não foi encontrado!", false);
            return;
        }

        txtCodigo.setText(String.valueOf(prod.getCodigo()));
        txtDescricao.setText(prod.getDescricao());
        cbxFornecedor.setValue(prod.getFornecedor());
        txtPreco.setText(String.valueOf(prod.getPreco()));

        btnAlterar.setDisable(false);
        btnDeletar.setDisable(false);
    }

    public void cancelarProduto(ActionEvent actionEvent) {
        try {
            if (!Show.MessageBox(Alert.AlertType.WARNING, "Tem certeza? Você vai perder as alterações não salvas!", true))
                return;

            if(txtCodigo.getText() == null)
                LimpaCampos();
            else{
                ProdutoVO prod = (ProdutoVO) dao.Ler(Integer.valueOf(txtPesquisa.getText()));

                txtCodigo.setText(String.valueOf(prod.getCodigo()));
                txtDescricao.setText(prod.getDescricao());
                cbxFornecedor.setValue(prod.getFornecedor());
                txtPreco.setText(String.valueOf(prod.getPreco()));
            }
            HabilitaCampos(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirProduto(ActionEvent actionEvent) {
        try {
            String msg = ValidaCampos();
            if (msg != null) {
                Show.MessageBox(Alert.AlertType.ERROR, msg, false);
                return;
            }

            ProdutoVO prod = new ProdutoVO();
            prod.setCodigo((txtCodigo.getText() == null || txtCodigo.getText().isEmpty()) ? 0 : Integer.valueOf(txtCodigo.getText().trim()));
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
        btnAlterar.setDisable(true);
        btnDeletar.setDisable(true);

        if (edit) {
            btnCadastrar.setDisable(true);
            btnCancelar.setDisable(false);
            btnSalvar.setDisable(false);

            txtDescricao.setDisable(false);
            cbxFornecedor.setDisable(false);
            txtPreco.setDisable(false);
            txtPesquisa.setDisable(true);
        } else {
            btnCadastrar.setDisable(false);
            btnCancelar.setDisable(true);
            btnSalvar.setDisable(true);

            txtDescricao.setDisable(true);
            cbxFornecedor.setDisable(true);
            txtPreco.setDisable(true);
            txtPesquisa.setDisable(false);
        }
    }

    private String ValidaCampos() {
        if (txtDescricao.getLength() < 10)
            return "A descrição deve ter no mínimo 10 caracteres!";
        if (cbxFornecedor.getValue() == null)
            return "Selecione um fornecedor!";
        if (txtPreco.getLength() < 3)
            return "Informe o preço unitário do produto!";
        return null;
    }

    private void LimpaCampos() {
        txtCodigo.setText(null);
        txtDescricao.setText(null);
        cbxFornecedor.setValue(null);
        txtPreco.setText(null);
        txtPesquisa.setText(null);
    }
}
