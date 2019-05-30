package fornecedor;

import DAO.FornecedorDAO;
import VO.FornecedorVO;
import VO.PadraoVO;
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
    @FXML
    TableView<PadraoVO> tbFornecedores;
    @FXML
    TableColumn<FornecedorVO, Integer> colCodigo;
    @FXML TableColumn<FornecedorVO, String> colNome, colCNPJ, colEndereco, colTelefone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<FornecedorVO, Integer>("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<FornecedorVO, String>("nome"));
        colCNPJ.setCellValueFactory(new PropertyValueFactory<FornecedorVO, String>("cnpj"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<FornecedorVO, String>("endereco"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<FornecedorVO, String>("telefone"));

        FornecedorDAO dao = new FornecedorDAO();
        ObservableList<PadraoVO> list = dao.Listar(true);
        if (list != null)
            tbFornecedores.setItems(list);
    }

    public void abrirFornecedores(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//fornecedor//fornecedor.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Fornecedores");
            stage.setScene(new Scene(loader));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
