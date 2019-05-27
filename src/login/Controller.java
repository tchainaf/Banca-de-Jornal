package login;

import DAO.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Show;


public class Controller {
    @FXML
    TextField txtUser;

    @FXML
    PasswordField txtSenha;

    private static boolean admin = false;

    public static boolean isAdmin() {
        return admin;
    }

    public void checkLogin(ActionEvent actionEvent) {
        try {
            if(txtUser.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                Show.MessageBox(Alert.AlertType.WARNING, "Informe o usuário e a senha", false);
                return;
            }

            String acesso = UsuarioDAO.Login(txtUser.getText(), txtSenha.getText());
            if(acesso == null) {
                Show.MessageBox(Alert.AlertType.ERROR, "Usuário e senha não correspondentes!", false);
                return;
            }
            if(acesso.equalsIgnoreCase("A"))
                admin = true;

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
