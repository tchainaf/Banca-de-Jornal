package compra_devolucao;

import VO.ProdutoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdd implements Initializable {
    @FXML TextField txtQtde, txtProduto;

    public static ProdutoVO produto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtProduto.setText(produto.getDescricao());
    }

    public void incluirItem(ActionEvent actionEvent) {
        try {
            produto.setQtde(Integer.valueOf(txtQtde.getText()));
            produto.setValorTotal(produto.getPreco() * produto.getQtde());
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
