package DAO;

import VO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecedorDAO extends PadraoDAO {

    public FornecedorDAO() {
        tabela = "FORNECEDOR";
        conn = Database.getConnection();
    }

    @Override
    public boolean Inserir(PadraoVO obj) {
        try {
            FornecedorVO forn = (FornecedorVO) obj;
            CallableStatement stm = conn.prepareCall("SP_INSERE_" + tabela);

            stm.setString("RAZAO_SOCIAL", forn.getNome());
            stm.setString("CNPJ", forn.getCNPJ());
            stm.setString("ENDERECO", forn.getEndereco());
            stm.setString("TELEFONE", forn.getTelefone());

            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Alterar(PadraoVO obj) {
        try {
            FornecedorVO forn = (FornecedorVO) obj;
            CallableStatement stm = conn.prepareCall("SP_ATUALIZA_" + tabela);

            stm.setInt("IDFORNECEDOR", forn.getCodigo());
            stm.setString("RAZAO_SOCIAL", forn.getNome());
            stm.setString("CNPJ", forn.getCNPJ());
            stm.setString("ENDERECO", forn.getEndereco());
            stm.setString("TELEFONE", forn.getTelefone());

            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Deletar(int id) {
        try {
            CallableStatement stm = conn.prepareCall("SP_EXCLUI_" + tabela);
            stm.setInt("IDFORNECEDOR", id);
            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PadraoVO Ler(int id) {
        try {
            CallableStatement stm = conn.prepareCall("SP_CONSULTA_" + tabela);
            stm.setInt("IDFORNECEDOR", id);
            ResultSet result = stm.executeQuery();

            FornecedorVO forn = new FornecedorVO();
            forn.setCodigo(result.getInt("IDFORNECEDOR"));
            forn.setNome(result.getString("RAZAO_SOCIAL"));
            forn.setCNPJ(result.getString("CNPJ"));
            forn.setEndereco(result.getString("ENDERECO"));
            forn.setTelefone(result.getString("TELEFONE"));

            return forn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<PadraoVO> Listar(boolean flag) {
        ObservableList<PadraoVO> list = FXCollections.observableArrayList();
        try {
            CallableStatement stm = conn.prepareCall("{call SP_LISTA_" + tabela + "}");
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                FornecedorVO forn = new FornecedorVO();
                forn.setCodigo(result.getInt("IDFORNECEDOR"));
                forn.setNome(result.getString("RAZAO_SOCIAL"));
                forn.setCNPJ(result.getString("CNPJ"));
                forn.setEndereco(result.getString("ENDERECO"));
                forn.setTelefone(result.getString("TELEFONE"));
                list.add(forn);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
