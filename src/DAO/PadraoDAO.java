package DAO;

import VO.PadraoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class PadraoDAO {
    protected static Connection conn;
    protected static String tabela;

    public static List<PadraoVO> Listar(){
        List<PadraoVO> list = new ArrayList<PadraoVO>();
        try{
            CallableStatement stm = conn.prepareCall("SP_LIST_" + tabela);
            ResultSet result = stm.executeQuery();
            while (result.next()){
                PadraoVO obj = new PadraoVO();
                obj.setId(result.getInt("ID"));
                list.add(obj);
            }
            return list;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    abstract public boolean Inserir(PadraoVO obj);

    abstract public boolean Alterar(PadraoVO obj);

    abstract public boolean Deletar(int id);

    abstract public PadraoVO Ler(int id);
}
