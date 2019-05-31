package produto;

import DAO.ProdutoDAO;
import VO.PadraoVO;
import VO.ProdutoVO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTable implements Initializable {
    @FXML TableView<PadraoVO> tbProdutos;
    @FXML TableColumn<ProdutoVO, Integer> colCodigo, colFornecedor, colQtde;
    @FXML TableColumn<ProdutoVO, String> colDescricao;
    @FXML TableColumn<ProdutoVO, Double> colPreco;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<ProdutoVO, String>("descricao"));
        colFornecedor.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("fornecedor"));
        colPreco.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("preco"));
        colQtde.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("qtdeEstoque"));

        ProdutoDAO dao = new ProdutoDAO();
        ObservableList<PadraoVO> list = dao.Listar(false);
        if(list != null)
            tbProdutos.setItems(list);

    }

    public void abrirProdutos(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//produto//produto.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Produtos");
            stage.setScene(new Scene(loader));
            stage.show();

            //TODO: atualizar tabela

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
