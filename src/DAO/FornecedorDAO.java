package DAO;

import VO.*;
import util.Database;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class FornecedorDAO extends PadraoDAO {

    public FornecedorDAO() {
        tabela = "tbFORNECEDOR";
        conn = Database.getConnection();
    }

    @Override
    public boolean Inserir(PadraoVO obj) {
        try {
            FornecedorVO forn = (FornecedorVO) obj;
            CallableStatement stm = conn.prepareCall("SP_INSERT_" + tabela);

            stm.setString("Nome", forn.getNome());
            stm.setString("Cnpj", forn.getCNPJ());
            stm.setString("Endereco", forn.getEndereco());
            stm.setString("Telefone", forn.getTelefone());

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
            CallableStatement stm = conn.prepareCall("SP_ALTER_" + tabela);

            stm.setInt("Id", forn.getCodigo());
            stm.setString("Nome", forn.getNome());
            stm.setString("Cnpj", forn.getCNPJ());
            stm.setString("Endereco", forn.getEndereco());
            stm.setString("Telefone", forn.getTelefone());

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

            FornecedorVO forn = new FornecedorVO();
            forn.setCodigo(result.getInt("Id"));
            forn.setNome(result.getString("Nome"));
            forn.setCNPJ(result.getString("Cnpj"));
            forn.setEndereco(result.getString("Endereco"));
            forn.setTelefone(result.getString("Telefone"));

            return forn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
