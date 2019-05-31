package usuario;

import DAO.UsuarioDAO;
import VO.PadraoVO;
import VO.UsuarioVO;
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
    TableView<PadraoVO> tbUsuarios;
    @FXML
    TableColumn<UsuarioVO, Integer> colCodigo;
    @FXML TableColumn<UsuarioVO, String> colNome, colAcesso;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<UsuarioVO, Integer>("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<UsuarioVO, String>("nome"));
        colAcesso.setCellValueFactory(new PropertyValueFactory<UsuarioVO, String>("tipoAcesso"));

        UsuarioDAO dao = new UsuarioDAO();
        ObservableList<PadraoVO> list = dao.Listar(true);
        if (list != null)
            tbUsuarios.setItems(list);
    }


    public void abrirUsuarios(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//usuario//usuario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Usuários");
            stage.setScene(new Scene(loader));
            stage.showAndWait();

            //TODO: atualizar tabela

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
