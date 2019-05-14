package DAO;

import VO.*;
import util.DBUtil;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class UsuarioDAO extends PadraoDAO {

    public UsuarioDAO() {
        tabela = "tbUSUARIO";
        conn = DBUtil.getConnection();
    }

    @Override
    public boolean Inserir(PadraoVO obj) {
        try {
            UsuarioVO user = (UsuarioVO) obj;
            CallableStatement stm = conn.prepareCall("SP_INSERT_" + tabela);

            stm.setString("Nome", user.getNome());
            stm.setString("Senha", user.getSenha());
            stm.setBoolean("Admin", user.isAdmin());

            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Alterar(PadraoVO obj) {
        try {
            UsuarioVO user = (UsuarioVO) obj;
            CallableStatement stm = conn.prepareCall("SP_ALTER_" + tabela);

            stm.setInt("Id", user.getCodigo());
            stm.setString("Nome", user.getNome());
            stm.setString("Senha", user.getSenha());
            stm.setBoolean("Admin", user.isAdmin());

            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Deletar(int id) {
        try {
            CallableStatement stm = conn.prepareCall("SP_DELETE_" + tabela);
            stm.setInt("Id", id);
            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PadraoVO Ler(int id) {
        try {
            CallableStatement stm = conn.prepareCall("SP_READ_" + tabela);
            stm.setInt("Id", id);
            ResultSet result = stm.executeQuery();

            UsuarioVO user = new UsuarioVO();
            user.setCodigo(result.getInt("Id"));
            user.setNome(result.getString("Nome"));
            user.setSenha(result.getString("Senha"));
            user.setAdmin(result.getBoolean("Admin"));

            return user;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
