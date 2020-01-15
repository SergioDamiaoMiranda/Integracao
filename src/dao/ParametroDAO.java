package dao;

import db.DaoException;
import db.DataBaseDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import to.ParametroTO;

public class ParametroDAO {

    public static Connection getConnection() throws SQLException {
        return DataBaseDaoFactory.getConnection();
    }

    public static ParametroTO detalharParametro() throws DaoException, SQLException {
        Connection con;
        PreparedStatement ps;
        try {
            StringBuilder sql;
            sql = new StringBuilder("select * from parametros where registro = \"1\"");
            con = getConnection();
            ps = con.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ParametroTO pto = new ParametroTO();
            while (rs.next()) {
                pto.setId(rs.getInt("id"));
                pto.setNomeMatriz(rs.getString("nome_matriz"));
                pto.setDiretorio(rs.getString("diretorio"));
                pto.setTempoPesquisa(rs.getInt("tempo_pesquisa"));
            }
            ps.close();
            con.close();
            return pto;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static int getIntervalo() throws DaoException {
        Connection con;
        PreparedStatement ps;
        int intervalo = 0;
        try {
            StringBuilder sql;
            sql = new StringBuilder("select * from parametro where id = \"1\"");
            con = getConnection();
            ps = con.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ParametroTO pto = new ParametroTO();
            while (rs.next()) {
                intervalo = rs.getInt("tempo_pesquisa");
            }
            ps.close();
            con.close();
            return intervalo * 1000;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static String getDiretorio() throws DaoException {
        Connection con;
        PreparedStatement ps;
        String diretorio = "c:\\integracao";
        try {
            StringBuilder sql;
            sql = new StringBuilder("select * from parametro where id = \"1\"");
            con = getConnection();
            ps = con.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            ParametroTO pto = new ParametroTO();
            while (rs.next()) {
                diretorio = rs.getString("diretorio");
            }
            ps.close();
            con.close();
            return diretorio;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }    
}
