package DAO;

import VO.PadraoVO;
import VO.UsuarioVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UsuarioDAO extends PadraoDAO {

    public UsuarioDAO() {
        conn = Database.getConnection();
    }

    public static String Login(String user, String senha){
        try {
            conn = Database.getConnection();
            CallableStatement stm = conn.prepareCall("{call SP_VALIDA_LOGIN (?, ?, ?)}");
            stm.setString("NOME_USUARIO", user);
            stm.setString("SENHA", senha);
            stm.registerOutParameter("ACESSO", Types.CHAR);

            stm.execute();

            String ret = stm.getString("ACESSO");
            return ret;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean Inserir(PadraoVO obj) {
        try {
            UsuarioVO user = (UsuarioVO) obj;
            CallableStatement stm = conn.prepareCall("{call SP_INSERE_USUARIO (?, ?, ?)}");

            stm.setString("NOME_USUARIO", user.getNome());
            stm.setString("SENHA", user.getSenha());
            stm.setString("ACESSO", user.isAdmin() ? "A" : "C");

            stm.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Alterar(PadraoVO obj) {
        try {
            UsuarioVO user = (UsuarioVO) obj;
            CallableStatement stm = conn.prepareCall("{call SP_ATUALIZA_USUARIO (?, ?, ?, ?)}");

            stm.setInt("IDUSUARIO", user.getCodigo());
            stm.setString("NOME_USUARIO", user.getNome());
            stm.setString("SENHA", user.getSenha());
            stm.setString("ACESSO", user.isAdmin() ? "A" : "C");

            stm.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Deletar(int id) {
        try {
            CallableStatement stm = conn.prepareCall("{call SP_EXCLUI_USUARIO (?)}");
            stm.setInt("IDUSUARIO", id);
            stm.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PadraoVO Ler(int id) {
        try {
            CallableStatement stm = conn.prepareCall("{call SP_CONSULTA_USUARIO (?)}");
            stm.setInt("IDUSUARIO", id);
            ResultSet result = stm.executeQuery();
            result.next();

            UsuarioVO user = new UsuarioVO();
            user.setCodigo(result.getInt("IDUSUARIO"));
            user.setNome(result.getString("NOME_USUARIO"));
            user.setSenha(result.getString("SENHA"));
            user.setAdmin(result.getString("ACESSO").equalsIgnoreCase("A") ? true : false);

            return user;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<PadraoVO> Listar(boolean flag) {
        ObservableList<PadraoVO> list = FXCollections.observableArrayList();
        try {
            CallableStatement stm = conn.prepareCall("{call SP_LISTA_USUARIO}");
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                UsuarioVO user = new UsuarioVO();
                user.setCodigo(result.getInt("IDUSUARIO"));
                user.setNome(result.getString("NOME_USUARIO"));
                user.setSenha(result.getString("SENHA"));
                user.setAdmin(result.getString("ACESSO").equalsIgnoreCase("A") ? true : false);
                list.add(user);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
