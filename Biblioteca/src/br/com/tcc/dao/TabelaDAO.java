package br.com.tcc.dao;

import br.com.tcc.bean.Coluna;
import br.com.tcc.bean.Tabela;
import br.com.tcc.conexao.DBConection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renan
 */
public class TabelaDAO {

    private static Connection conexao;

    public List<Tabela> listaTabelas() {
        List<Tabela> listaTabela = new ArrayList<>();
        try {
            conexao = DBConection.getPostgres();
            DatabaseMetaData dmd = conexao.getMetaData();
            ResultSet rs = dmd.getTables(null, "public", null, new String[]{"TABLE"});
            while (rs.next()) {
                Tabela tab = new Tabela();
                tab.setNome(rs.getString(3));
                listaTabela.add(tab);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TabelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TabelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TabelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTabela;
    }

    public List<Coluna> listaColunas(Tabela tabela) {
        List<Coluna> listaColuna = new ArrayList<>();
        try {
            DatabaseMetaData dmd = conexao.getMetaData();
            ResultSet rs = dmd.getColumns(null, "public", tabela.getNome(), null);
            while (rs.next()) {
                Coluna c = new Coluna();
                c.setNome(rs.getString(4));
                listaColuna.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TabelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaColuna;
    }
}
