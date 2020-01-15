package dao;

import db.DaoException;
import db.DataBaseDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class HArquivoDAO {

    java.util.Date hoje = new java.util.Date();
    private static final String INSERT_SQL
            = "INSERT INTO hist_processamento "
            + "(arquivo, processado, data_cadastro, hora_cadastro)"
            + "VALUES(?,?,?,?)";

    public static Connection getConnection() throws SQLException {
        return DataBaseDaoFactory.getConnection();
    }

    public static void inserirLoja(String arq, String proc) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;        // Data Hora Systema
        String data = "yyyy/MM/dd";
        String hora = "HH:mm";
        String dataS;
        String horaS;
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        dataS = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        horaS = formata.format(agora);

        try {
            int i = 1;
            con = getConnection();
            ps = con.prepareStatement(INSERT_SQL);
            ps.setString(i++, arq);
            ps.setString(i++, proc);
            ps.setString(i++, dataS);
            ps.setString(i++, horaS);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException ex) {
                throw new DaoException(ex);
            }
        }
    }

}
