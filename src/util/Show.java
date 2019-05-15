package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class Show {

    public static boolean MessageBox(Alert.AlertType tipo, String mensagem, boolean botoes){
        Alert msg;
        if (!botoes) {
            msg = new Alert(tipo, mensagem);
            msg.setHeaderText(null);
            msg.show();
        } else {
            msg = new Alert(tipo, mensagem, ButtonType.YES, ButtonType.NO);
            msg.setHeaderText(null);
            Optional<ButtonType> result = msg.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES)
                return true;
        }
        return false;
    }
}
