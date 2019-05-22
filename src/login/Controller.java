package login;

import DAO.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    @FXML
    TextField txtUser, txtSenha;

    public void checkLogin(ActionEvent actionEvent) {
        try {

            //chamar DAO para validar usuario e senha

            /*if(!UsuarioDAO.Login(txtUser.getText(), txtSenha.getText())) {
                Show.MessageBox(Alert.AlertType.ERROR, "Usuário e senha não correspondentes!", false);
                return;
            }*/

            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();

            Stage novaTela = new Stage();
            novaTela.setTitle("Banca de Jornal");
            novaTela.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("..//principal//principal.fxml"))));
            novaTela.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
