package venda;

import DAO.ProdutoDAO;
import VO.PadraoVO;
import VO.ProdutoVO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Show;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdd implements Initializable {
    @FXML ComboBox<PadraoVO> cbxItens;
    @FXML TextField txtQtde;
    @FXML Label lblEstoque;

    public static ProdutoVO produto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProdutoDAO prodDAO = new ProdutoDAO();
        ObservableList<PadraoVO> list = prodDAO.Listar(true);
        if (list != null && list.size() > 0)
            cbxItens.setItems(list);
        else {
            Show.MessageBox(Alert.AlertType.WARNING, "Não há produtos em estoque!", false);
            Stage tela = (Stage) txtQtde.getScene().getWindow();
            tela.close();
        }
    }

    public void incluirItem(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();

            if(txtQtde.getText() == null || txtQtde.getText().isEmpty()){
                Show.MessageBox(Alert.AlertType.ERROR, "Informe a quantidade!", false);
                return;
            }

            //TODO: checar qtde com o estoque
            ProdutoVO item = (ProdutoVO) cbxItens.getValue();
            item.setQtde(Integer.getInteger(txtQtde.getText().trim()));
            item.setValorTotal(item.getQtde() * produto.getPreco());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeItem(ActionEvent actionEvent) {
        produto = (ProdutoVO) cbxItens.getValue();
        lblEstoque.setText("Em estoque: " + produto.getQtdeEstoque());
    }
}
