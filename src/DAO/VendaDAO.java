package DAO;

import VO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

import java.sql.*;

public class VendaDAO extends PadraoDAO {
    public int idMov = 0;

    public VendaDAO() {
        conn = Database.getConnection();
    }

    @Override
    public boolean Inserir(PadraoVO obj) {
        try {
            VendaVO venda = (VendaVO) obj;
            CallableStatement stm = conn.prepareCall("{call SP_INSERE_VENDA (?, ?, ?, ?)}");

            stm.setDouble("PRECO_VENDA", venda.getPreco());
            stm.setDate("DATA_VENDA", new java.sql.Date(venda.getData().getDate()));
            stm.setString("TP_PAGAMENTO", venda.getTipo());
            stm.registerOutParameter("IDVENDA", Types.INTEGER);

            stm.execute();
            idMov = stm.getInt("IDVENDA");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Alterar(PadraoVO obj) {
        try {
            VendaVO venda = (VendaVO) obj;
            CallableStatement stm = conn.prepareCall("{call SP_ATUALIZA_VENDA (?, ?, ?, ?)}");

            stm.setInt("IDVENDA", venda.getCodigo());
            stm.setDouble("PRECO_VENDA", venda.getPreco());
            stm.setDate("DATA_VENDA", new java.sql.Date(venda.getData().getDate()));
            stm.setString("TP_PAGAMENTO", venda.getTipo());

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
            CallableStatement stm = conn.prepareCall("{call SP_EXCLUI_VENDA (?)}");
            stm.setInt("IDVENDA", id);
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
            CallableStatement stm = conn.prepareCall("{call SP_CONSULTA_VENDA (?)}");
            stm.setInt("IDVENDA", id);
            ResultSet result = stm.executeQuery();
            result.next();

            VendaVO venda = new VendaVO();
            venda.setCodigo(result.getInt("IDVENDA"));
            venda.setPreco(result.getDouble("PRECO_VENDA"));
            venda.setData(result.getDate("DATA_VENDA"));
            venda.setTipo(result.getString("TP_PAGAMENTO"));

            return venda;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<PadraoVO> Listar(boolean flag) {
        ObservableList<PadraoVO> list = FXCollections.observableArrayList();
        try {
            CallableStatement stm = conn.prepareCall("{call SP_LISTA_VENDA}");
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                VendaVO venda = new VendaVO();
                venda.setCodigo(result.getInt("IDVENDA"));
                venda.setPreco(result.getDouble("PRECO_VENDA"));
                venda.setData(result.getDate("DATA_VENDA"));
                venda.setTipo(result.getString("TP_PAGAMENTO"));
                list.add(venda);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean AddItens(ObservableList<PadraoVO> list, int idVenda) {
        try {
            for (PadraoVO item : list) {

                ProdutoVO prod = (ProdutoVO) item;
                prod.setIdMov(idVenda);
                CallableStatement stm = conn.prepareCall("{call SP_INSERE_PRODUTO_VENDA (?, ?, ?, ?)}");

                stm.setInt("IDPRODUTO", prod.getCodigo());
                stm.setInt("IDVENDA", prod.getIdMov());
                stm.setInt("QUANTIDADE_VENDA", prod.getQtde());
                stm.setDouble("PRECO_UNITARIO", prod.getPreco());

                stm.execute();
            }
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
